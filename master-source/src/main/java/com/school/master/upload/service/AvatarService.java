package com.school.master.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface AvatarService {
    int uploadImage(Principal principal, MultipartFile fileUpload);

    String getImageUrl(Principal principal);
}
