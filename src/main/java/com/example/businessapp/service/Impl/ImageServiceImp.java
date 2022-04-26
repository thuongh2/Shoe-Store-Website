package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.ImageDto;
import com.example.businessapp.entity.Image;
import com.example.businessapp.entity.Product;
import com.example.businessapp.exception.ProductNotFoundException;
import com.example.businessapp.repository.ImageRepository;
import com.example.businessapp.repository.ProductRepository;
import com.example.businessapp.service.CloudinaryService;
import com.example.businessapp.service.ImageService;
import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class ImageServiceImp implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<ImageDto> getImageOfProduct(Long id) {

        // convert productRequest to product
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Image: Product with id %d not found", id)));

        return ObjectMapperUtils.mapAll(imageRepository.findByProductAndActiveIsTrue(product), ImageDto.class);
    }

    @Override
    public String saveImage(MultipartFile image, Long productId) throws ProductNotFoundException{
        log.info("Upload file");
        String img = cloudinaryService.uploadFile(image);

        if(img == null){
            throw new ProductNotFoundException("File not upload");
        }
        log.info("Save successfully!");
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Image: Product with id %d not found", productId)));

        Image imageProduct = new Image();
        imageProduct.setThumbnail(img);
        imageProduct.setProduct(product);

        return imageRepository.save(imageProduct).getThumbnail();

    }

    @Override
    public void delete(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Image "+ id +" not found"));

        image.setActive(!image.isActive());

        imageRepository.save(image);
    }

    // delete for super-admin
    @Override
    public void deleteByAdmin(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Image "+ id +" not found"));
        imageRepository.delete(image);
    }
}
