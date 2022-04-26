package com.example.businessapp.controller;

import com.example.businessapp.dto.OrdersDto;
import com.example.businessapp.service.OrderDetailService;
import com.example.businessapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/save/order")
    public OrdersDto saveOrder(@Valid @ModelAttribute OrdersDto orders){
        return orderService.createOrder(orders);
    }

    @PostMapping("/update/order")
    public OrdersDto updateOrder(@Valid @ModelAttribute OrdersDto orders) throws Exception {
        return  orderService.update(orders);
    }

    //ADMIN
    @GetMapping("/s/order/all")
    public List<OrdersDto> getAll(){
        return orderService.getOrders();
    }


    @GetMapping("/order/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        orderService.deleteOrder(id);
    }

    //ADMIN
    @GetMapping("/s/order/delete/{id}")
    public void deleteByAdmin(@PathVariable(name = "id") Long id){
        orderService.deleteOrder(id);
    }






}
