package com.school.master.upload.test;


import com.school.master.common.config.FtpUploadConfig;
import com.school.master.common.net.FtpConnectionPool;
import com.school.master.common.net.MyFtpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootTest
public class FtpPoolTest {
    @Autowired
    private FtpUploadConfig ftpConfig;
    private FtpConnectionPool ftpPool;

    @Test
    public void poolTest() {
        ftpPool = FtpConnectionPool.createPool(ftpConfig);
        MyFtpClient ftpClient = ftpPool.getMyFtpClient();
        ftpPool.replaceMapPool(ftpClient);
    }

}
