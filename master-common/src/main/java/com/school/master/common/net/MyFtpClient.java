package com.school.master.common.net;

import cn.hutool.extra.ftp.Ftp;

public class MyFtpClient {
    private Ftp ftp;

    public MyFtpClient(String host, int port, String username, String password) {
        ftp = new Ftp(host, port, username, password);
    }

}
