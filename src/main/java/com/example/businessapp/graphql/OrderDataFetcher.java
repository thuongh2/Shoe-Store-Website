package com.example.businessapp.graphql;

import com.example.businessapp.entity.Orders;
import com.example.businessapp.manager.BaseService;
import com.example.businessapp.utils.Extensions;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.experimental.ExtensionMethod;

import java.util.List;

@DgsComponent
@ExtensionMethod(Extensions.class)
public class OrderDataFetcher extends BaseService {
    @DgsQuery
    public List<Orders> getAllOrder(){
        return orderRepository.findAll();
    }

    @DgsQuery
    public Orders getOrderById(@InputArgument(name = "orderId") String orderId){
        return orderRepository.findById(orderId).get();
    }

    @DgsQuery
    public List<Orders> getOrderOfUser(@InputArgument(name = "userId") String userId){
        return orderRepository.findByUserId(userId);
    }

    @DgsMutation
    public String createOrder(@InputArgument(name = "order") Orders order){
        orderRepository.save(order);
        return order.getId();
    }

    @DgsMutation
    public String updateOrder(@InputArgument(name = "order") Orders order) throws Exception {
        if(order.getId().isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        orderRepository.save(order);
        return order.getId();
    }

    @DgsMutation
    public boolean deleteOrder(@InputArgument(name = "orderId") String orderId) throws Exception {
        if(orderId.isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        Orders orders = orderRepository.findById(orderId).get();
        orderRepository.delete(orders);
        return true;
    }
}
