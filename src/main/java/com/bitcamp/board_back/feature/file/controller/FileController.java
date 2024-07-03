package com.bitcamp.board_back.feature.file.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired

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

    @GetMapping("/download/{filePath}")
    public ResponseEntity<InputStreamResource> downloadFile(
            @AuthenticationPrincipal AccountUserDetails accountUserDetails,
            @PathVariable String filePath) {
        try {
            InputStream fileStream = fileService.downLoad(filePath, accountUserDetails);

            // Set content disposition to "attachment" to force download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", filePath);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(fileStream));
        } catch (Exception e) {
            // Handle exception and return appropriate response
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
