package com.school.master.common.net;

import com.school.master.common.config.FtpUploadConfig;
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
public class FtpConnectionPool {
    private FtpUploadConfig ftpConfig;
    private static FtpConnectionPool ftpConnectionPool;
    private Integer currentMaxPool;
    private volatile static Map<Integer, MyFtpClient> ftpPoolMap;
    Class clazz;
    private Logger logger = LoggerFactory.getLogger(FtpConnectionPool.class);

    private FtpConnectionPool(FtpUploadConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
        this.currentMaxPool = ftpConfig.getMaxPool();
        ftpPoolMap = new ConcurrentHashMap<>(ftpConfig.getMaxPool());
        for (int key = 0; key < ftpConfig.getMaxPool(); key++) {
            MyFtpClient ftpClient = new MyFtpClient(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword());
            ftpPoolMap.put(ftpClient.hashCode(), ftpClient);
        }
    }


    public static FtpConnectionPool createPool(FtpUploadConfig ftpConfig) {
        if (ftpPoolMap == null) {
            synchronized (FtpConnectionPool.class) {
                if (ftpPoolMap == null) {
                    ftpConnectionPool = new FtpConnectionPool(ftpConfig);
                }
            }
        }
        return ftpConnectionPool;
    }

    /**
     * 取出一个连接
     *
     * @return
     */

    public MyFtpClient getMyFtpClient() {
        synchronized (FtpConnectionPool.class) {
            for (Integer key : ftpPoolMap.keySet()) {
                if (ftpPoolMap.get(key) != null) {
                    return ftpPoolMap.get(key);
                }
            }
            MyFtpClient ftpClient = new MyFtpClient(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword());
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

    public void replaceMapPool(MyFtpClient ftpClient) {
        for (Integer key : ftpPoolMap.keySet()) {
            if (key.equals(ftpClient.hashCode())) {
                ftpPoolMap.put(key, ftpClient);
                logger.info("放回连接hashcode:{}",key);
                ftpClient=null;
            }
        }
    }


    public FtpUploadConfig getFtpConfig() {
        return ftpConfig;
    }

    public void setFtpConfig(FtpUploadConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }
}
