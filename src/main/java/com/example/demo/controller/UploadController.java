package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.utils.AliyunOSSperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController

public class UploadController {
    @Autowired
    private AliyunOSSperator aliyunOSSperator;

    @PostMapping("/upload")
    public Result uplode(MultipartFile file) throws Exception {
        log.info("文件上传:{}",file.getOriginalFilename());
        /*将文件交给OSS存储管理*/
        aliyunOSSperator.uplode(file.getBytes(),file.getOriginalFilename());
        String url = aliyunOSSperator.uplode(file.getBytes(),file.getOriginalFilename());
        log.info("文件上穿到OSS, url: {} ",url);
        return Result.success(url);
    }
}
