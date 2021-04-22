package com.school.master.admin;

import cn.hutool.crypto.digest.MD5;
import com.school.master.common.utils.BaseVaildUtil;
import com.school.master.common.utils.PasswordEnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class EncryTest extends AbstractTestNGSpringContextTests {
    @Autowired
    MD5 md5;
    @Autowired
    PasswordEnCodeUtil passwordEnCodeUtil;

    @Test
    public void md5() {
        System.out.println(this.md5.digestHex("123456"));
    }

    @Test
    public void reg() {
        boolean legal = passwordEnCodeUtil.isLegal("Admin123");
        System.out.println(legal);
    }

    @Test
    public void passMd5() {
        MD5 md5 = new MD5("256".getBytes());
        String s = md5.digestHex("12345");
        String s1 = this.md5.digestHex("12345");
        System.out.println(passwordEnCodeUtil.matches(s, s1));
    }

    @Test
    public void vaildTest() {
        boolean b = BaseVaildUtil.equalsPlus(new Integer(3), new Integer(1), new Integer(2));
        System.out.println(b);
    }


}
