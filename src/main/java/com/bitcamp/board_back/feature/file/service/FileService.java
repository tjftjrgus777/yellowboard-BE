package com.bitcamp.board_back.feature.file.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

public interface FileService {

    String upload(MultipartFile file, AccountUserDetails accountUserDetails);

    Resource getiImage(String fileName, AccountUserDetails accountUserDetails);

}
