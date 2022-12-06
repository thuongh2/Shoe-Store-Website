package com.example.businessapp.manager;

import com.example.businessapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected CommentRepository commentRepository;

    @Autowired
    protected OrderItemRepository orderItemRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected PaymentRepository paymentRepository;

    @Autowired
    protected UserRepository userRepository;

}
