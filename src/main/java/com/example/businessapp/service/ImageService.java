package com.example.businessapp.service;

import com.example.businessapp.dto.ImageDto;
import com.example.businessapp.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {

    List<ImageDto> getImageOfProduct(Long id);

    String saveImage(MultipartFile image, Long productId);

    void delete(Long id);

    void deleteByAdmin(Long id);
}
