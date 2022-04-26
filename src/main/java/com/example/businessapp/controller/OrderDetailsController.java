package com.example.businessapp.controller;

import com.example.businessapp.dto.OrderDetailDto;
import com.example.businessapp.dto.OrderDetailRequest;
import com.example.businessapp.securiry.CustomUserDetails;
import com.example.businessapp.service.Impl.EmailSenderService;
import com.example.businessapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderDetailsController {

    //update orders
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/s/orderdetails")
    public List<OrderDetailDto> getOrderDetails(){
        return orderDetailService.getOrderDetails();
    }

    @GetMapping("/s/orderdetail/product")
    public List<OrderDetailDto> getOrder(@RequestParam(name = "product") Long productId){
        return orderDetailService.getOrderDetailsByProduct(productId);
    }

    //get from context spring security
    @GetMapping("/orderdetail/me")
    public List<OrderDetailDto> getOrderByUser(@AuthenticationPrincipal CustomUserDetails currentUser){
        return orderDetailService.getOrderDetailsByUser(currentUser.getUsername());
    }

    @PostMapping("/orderdetail/email")
    public List<OrderDetailDto> getOrderByEmail(@ModelAttribute(name = "email") String email){
        return orderDetailService.getOrderDetailsByEmail(email);
    }

    // send for shop and sent for user
    @GetMapping("/mail")
    public void sendEmail(){
        emailSenderService.sendEmail("dung2002215@gmail.com", "Dung dep trai vll", "Hom qua em tuyet lam");
    }

    @PostMapping("/order/detail")
    public OrderDetailDto createOrderDetail(@Valid @ModelAttribute OrderDetailRequest orderDetailRequest)
                                            throws IOException {
        return orderDetailService.createOrderDetail(orderDetailRequest);
    }

    @GetMapping("/orderdetail/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delele(@PathVariable(name = "id") Long id){
        orderDetailService.deleteOrderDetail(id);
    }

    @GetMapping("/s/orderdetail/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleleByAdmin(@PathVariable(name = "id") Long id){
        orderDetailService.deleteOrderDetailByUser(id);
    }

    @GetMapping("/s/checkorder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void checkOrder(@PathVariable(name = "id") Long id){
        orderDetailService.checkOrderDetail(id);
    }





}
