package com.example.businessapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface CloudinaryService {

    String uploadFile(MultipartFile gif);

    File convertMultiPartToFile(MultipartFile file) throws IOException;


}
