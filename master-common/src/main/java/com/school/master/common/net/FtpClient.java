package com.school.master.common.net;

import cn.hutool.extra.ftp.Ftp;
import com.school.master.common.config.FtpClientConfig;
import com.school.master.common.exception.Asserts;

import java.io.File;

public class FtpClient {
    private FtpClientConfig ftpConfig;
    private Ftp ftp;

    public FtpClient(FtpClientConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
        init();
    }

    private void init() {
        ftp = new Ftp(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword());
    }

    public String uploadFile(String path, File file) {
        //判断类型是否合法
        boolean suffixCensor = suffixCensor(file);
        if (!suffixCensor) {
            Asserts.fail("该文件类型不允许上传");
        }
        //判断文件大小是合法
        boolean fileSizeCensor = fileSizeCensor(file);
        if (!fileSizeCensor) {
            Asserts.fail("文件过大,请勿上传");
        }
        //上传文件
        //判断上传是否超时(在新线程中)
        boolean upload = ftp.upload(path, file);
        //判断上传是否成功

        //成功返回路径
        //不成功返回错误消息
        return "";
    }

    private boolean suffixCensor(File file) {
        String name = file.getName();
        String[] split = name.split(".");
        String suffix = split[split.length - 1];
//        ftpConfig.get
//        for(){
//
//        }
        return false;
    }

    private boolean fileSizeCensor(File file) {

        return false;
    }


}
