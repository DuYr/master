package com.school.master.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @FileName: FtpUploadConfig
 * @Author: LeeDream
 */
@Component
@ConfigurationProperties(prefix = "vsftpd.connection")
public class FtpUploadConfig {
    private String host;
    private Integer port = 21;
    private String username;
    private String password;
    private Integer maxPool = 10;
    private Integer timeOut = 500;
    @Value("${upload.file.images}")
    private String images = "";

    public FtpUploadConfig() {
    }

    public FtpUploadConfig(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public FtpUploadConfig(String host, Integer port, String username, String password, Integer maxPool, Integer timeOut) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.maxPool = maxPool;
        this.timeOut = timeOut;
    }

    public FtpUploadConfig(String host, Integer port, String username, String password, Integer maxPool, Integer timeOut, String images) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.maxPool = maxPool;
        this.timeOut = timeOut;
        this.images = images;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxPool() {
        return maxPool;
    }

    public void setMaxPool(Integer maxPool) {
        this.maxPool = maxPool;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
