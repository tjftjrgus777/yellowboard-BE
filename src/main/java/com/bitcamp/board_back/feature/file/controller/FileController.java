package com.bitcamp.board_back.feature.file.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitcamp.board_back.feature.file.service.FileService;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal AccountUserDetails accountUserDetails) {
        return fileService.upload(file, accountUserDetails);
    }

    @GetMapping(value = "{fileName}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public Resource getImage(
            @PathVariable("fileName") String fileName,
            @AuthenticationPrincipal AccountUserDetails accountUserDetails) {
        return fileService.getiImage(fileName, accountUserDetails);
    }

}
