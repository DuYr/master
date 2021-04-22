package com.school.master.upload.service.impl;

import com.school.master.upload.dao.AdminImageDao;
import com.school.master.upload.dto.AdminImage;
import com.school.master.upload.service.AdminImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service("adminImageService")
public class AdminImageServiceImpl implements AdminImageService {
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
