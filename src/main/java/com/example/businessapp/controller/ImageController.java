package com.example.businessapp.controller;


import com.example.businessapp.dto.ImageDto;
import com.example.businessapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/s/images")
    public List<ImageDto> getImages(Long id){
        return imageService.getImageOfProduct(id);
    }

    @PostMapping("/save")
    public String saveImage(@ModelAttribute(name="image") MultipartFile image,
                            @ModelAttribute(name="productId") Long productId){
        return imageService.saveImage(image, productId);
    }
}
