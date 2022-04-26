package com.example.businessapp.service;

import com.example.businessapp.dto.SizeDto;
import java.util.List;

public interface SizeService {

    List<SizeDto> getSizes();

    SizeDto createSize(SizeDto sizeDto);

    SizeDto updateSize(SizeDto sizeDto);

    void deleteSize(Long id);

    void deleteSizeByAdmin(Long id);


}
