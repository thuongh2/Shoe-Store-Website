package com.example.businessapp.graphql;

import com.example.businessapp.entity.OrderItem;
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
public class OrderItemDataFetcher extends BaseService {

    @DgsQuery
    public OrderItem getOrderItem(@InputArgument(name = "orderId") String orderId){
        return orderItemRepository.findById(orderId).get();
    }

    @DgsQuery
    public List<OrderItem> getItemsOfOrder(@InputArgument(name = "orderId") String orderId){
        return orderItemRepository.findByOrderId(orderId);
    }

    @DgsMutation
    public String createOrderItem(@InputArgument(name = "orderItem") OrderItem order) {
        orderItemRepository.save(order);
        return order.getId();
    }

    @DgsMutation
    public String updateOrderItem(@InputArgument(name = "orderItem") OrderItem order) throws Exception {
        if (order.getId().isBlankOrNull()) {
            throw new Exception("Không tìm thấy sản phẩm");
        }
        orderItemRepository.save(order);
        return order.getId();
    }

    @DgsMutation
    public boolean deleteOrderItem(@InputArgument(name = "orderItemId") String orderId) throws Exception {
        if (orderId.isBlankOrNull()) {
            throw new Exception("Không tìm thấy sản phẩm");
        }
        OrderItem orders = orderItemRepository.findById(orderId).get();
        orderItemRepository.delete(orders);
        return true;
    }
}
