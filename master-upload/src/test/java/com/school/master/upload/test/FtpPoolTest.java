package com.school.master.upload.test;


import com.school.master.common.net.FtpClient;
import com.school.master.common.net.FtpPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class FtpPoolTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private FtpPool ftpClient;

    @Test
    public void poolTest() {

    }

}
