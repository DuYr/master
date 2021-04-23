package com.school.master.upload.service.impl;

import com.school.master.common.net.FtpClient;
import com.school.master.mapper.AdminImageDao;
import com.school.master.model.AdminImage;
import com.school.master.upload.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service("adminImageService")
public class AvatarServiceImpl implements AvatarService {
    @Autowired
    private FtpClient ftpClient;
    @Autowired
    private AdminImageDao adminImageDao;
    @Override
    public int uploadImage(Principal principal, MultipartFile fileUpload) {
        if (principal == null) {

        }
        AdminImage adminImage = new AdminImage();
        return 0;
    }

    @Override
    public String getImageUrl(Principal principal) {
        return null;
    }

}
