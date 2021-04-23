package com.school.master.common.net;

import com.school.master.common.config.FtpClientConfig;

public interface FtpPool {


    FtpClient getFtpClient();

    void replaceMapPool(FtpClient ftpClient);

    void clearPool();

    FtpClientConfig getFtpConfig();

    void setFtpConfig(FtpClientConfig ftpConfig);
}
