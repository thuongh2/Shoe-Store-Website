//package com.example.businessapp.controller;
//
//import com.example.businessapp.dto.SizeDto;
//import com.example.businessapp.service.SizeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "https://shoes-store-udtt.vercel.app/")
//public class SizeController {
//
//    @Autowired
//    private SizeService sizeService;
//
//    @GetMapping("/sizes")
//    public List<SizeDto> getSizes(){
//        return  sizeService.getSizes();
//    }
//
//    @PostMapping("/s/size/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public SizeDto createSize(@ModelAttribute SizeDto sizeDto){
//        return sizeService.createSize(sizeDto);
//    }
//
//    @PostMapping("/s/size/update")
//    public SizeDto updateSize(@ModelAttribute SizeDto sizeDto){
//        return sizeService.updateSize(sizeDto);
//    }
//
//    @GetMapping("/s/delete/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable(name = "id") Long id){
//        sizeService.deleteSize(id);
//    }
//
//    @GetMapping("/s/delete-admin/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteByAdmin(@PathVariable(name = "id") Long id){
//        sizeService.deleteSizeByAdmin(id);
//    }
//
//}
