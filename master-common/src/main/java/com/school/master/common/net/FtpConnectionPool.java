package com.school.master.common.net;

import com.school.master.common.config.FtpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: FtpConnectionPool
 * @Author: LeeDream
 * @Date: 2021/4/19 23:54
 * @Description: ftp对象中连接池
 * @Version 1.0.0
 */
public class FtpConnectionPool implements FtpPool {
    private FtpClientConfig ftpConfig;
    private static FtpConnectionPool ftpPool;
    private Integer currentMaxPool;
    private volatile static Map<Integer, FtpClient> ftpPoolMap;
    private Logger logger = LoggerFactory.getLogger(FtpConnectionPool.class);

    private FtpConnectionPool(FtpClientConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
        this.currentMaxPool = ftpConfig.getMaxPool();
        ftpPoolMap = new ConcurrentHashMap<>(ftpConfig.getMaxPool());
        for (int key = 0; key < ftpConfig.getMaxPool(); key++) {
            FtpClient ftpClient = new FtpClient(ftpConfig);
            ftpPoolMap.put(ftpClient.hashCode(), ftpClient);
        }
    }

    public static FtpConnectionPool createPool(FtpClientConfig ftpClientConfig) {
        if (ftpPoolMap == null) {
            synchronized (FtpConnectionPool.class) {
                if (ftpPoolMap == null) {
                    ftpPool = new FtpConnectionPool(ftpClientConfig);
                }
            }
        }
        return ftpPool;
    }

    /**
     * 取出一个连接
     *
     * @return
     */
    @Override
    public FtpClient getFtpClient() {
        synchronized (FtpConnectionPool.class) {
            for (Integer key : ftpPoolMap.keySet()) {
                if (ftpPoolMap.get(key) != null) {
                    return ftpPoolMap.get(key);
                }
            }
            FtpClient ftpClient = new FtpClient(ftpConfig);
            this.currentMaxPool++;
            ftpPoolMap.put(ftpClient.hashCode(), ftpClient);
            //占位
            ftpPoolMap.put(ftpClient.hashCode(), null);
            logger.info("取出连接hashcode:{}", ftpClient.hashCode());
            return ftpClient;
        }

    }

    /**
     * 放回池中
     *
     * @param ftpClient
     */
    @Override
    public void replaceMapPool(FtpClient ftpClient) {
        for (Integer key : ftpPoolMap.keySet()) {
            if (key.equals(ftpClient.hashCode())) {
                ftpPoolMap.put(key, ftpClient);
                logger.info("放回连接hashcode:{}", key);
                ftpClient = null;
            }
        }
    }

    @Override
    public void clearPool() {
        FtpConnectionPool.ftpPoolMap.forEach((key, value) -> value = null);
        ftpPool = null;
        System.gc();
    }

    @Override
    public FtpClientConfig getFtpConfig() {
        return ftpConfig;
    }

    @Override
    public void setFtpConfig(FtpClientConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }
}
