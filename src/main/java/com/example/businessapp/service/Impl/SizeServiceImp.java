package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.SizeDto;
import com.example.businessapp.entity.Product;
import com.example.businessapp.entity.Size;
import com.example.businessapp.exception.NotFoundException;
import com.example.businessapp.repository.ProductRepository;
import com.example.businessapp.repository.SizeRepository;
import com.example.businessapp.service.ProductService;
import com.example.businessapp.service.SizeService;
import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SizeServiceImp implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ProductRepository productRepository;

    //get all size for admin
    @Override
    public List<SizeDto> getSizes() {
        return ObjectMapperUtils.mapAll(sizeRepository.findAll(), SizeDto.class);
    }

    /* create size for product by admin */
    @Override
    public SizeDto createSize(SizeDto sizeDto) {

        // get product contain size
        Product product = productRepository.findById(sizeDto.getProductDto().getId())
                .orElseThrow(()-> new NotFoundException("product"+ sizeDto.getProductDto().getId() +" not found"));

        Size size = ObjectMapperUtils.map(sizeDto, Size.class);
        // set size of product
        size.setProduct(product);

        return ObjectMapperUtils.map(sizeRepository.save(size), SizeDto.class);
    }

    @Override
    public SizeDto updateSize(SizeDto sizeDto) {

        Size size = sizeRepository.findById(sizeDto.getId())
                .orElseThrow(()-> new NotFoundException("size not found"));
        // update size
        size.setSize(sizeDto.getSize());
        size.setQuantity(sizeDto.getQuantity());

        log.info(String.format("%s update to %s", size.getSize(), sizeDto.getSize()));
        log.info(String.format("%s update quantity to %s", size.getQuantity(), sizeDto.getQuantity()));

        return ObjectMapperUtils.map(sizeRepository.save(size), SizeDto.class);
    }

    @Override
    public void deleteSize(Long id) {
        /* get size current */
        Size size = sizeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("size not found"));

        size.setActive(!size.isActive());

        sizeRepository.save(size);
    }

    // delete size in database by admin
    @Override
    public void deleteSizeByAdmin(Long id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("size not found"));

        sizeRepository.delete(size);
    }
}
