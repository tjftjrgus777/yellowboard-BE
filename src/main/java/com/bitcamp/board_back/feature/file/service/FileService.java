package com.bitcamp.board_back.feature.file.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile file);

    Resource getiImage(String fileName);

}
