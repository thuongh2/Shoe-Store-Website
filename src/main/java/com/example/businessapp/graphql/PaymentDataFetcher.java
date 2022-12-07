package com.example.businessapp.graphql;

import com.example.businessapp.entity.Orders;
import com.example.businessapp.entity.Payment;
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
public class PaymentDataFetcher extends BaseService {
    @DgsQuery
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @DgsQuery
    public Payment getPaymentById(@InputArgument(name = "paymentId") String paymentId){
        return paymentRepository.findById(paymentId).get();
    }

    @DgsQuery
    public Payment getPaymentByOrder(@InputArgument(name = "orderId") String orderId){
        return paymentRepository.findByOrderId(orderId);
    }

    @DgsQuery
    public List<Payment> getPaymentByProduct(@InputArgument(name = "productId") String productId){
        return paymentRepository.findByProductId(productId);
    }

    @DgsMutation
    public String createPayment(@InputArgument(name = "payment") Payment payment) {
        paymentRepository.save(payment);
        return payment.getId();
    }

    @DgsMutation
    public String updatePayment(@InputArgument(name = "payment") Payment payment) throws Exception {
        if (payment.getId().isBlankOrNull()) {
            throw new Exception("Không tìm thấy sản phẩm");
        }
        paymentRepository.save(payment);
        return payment.getId();
    }

    @DgsMutation
    public boolean deletePayment(@InputArgument(name = "paymentId") String paymentId) throws Exception {
        if (paymentId.isBlankOrNull()) {
            throw new Exception("Không tìm thấy sản phẩm");
        }
        Payment payment = paymentRepository.findById(paymentId).get();
        paymentRepository.delete(payment);
        return true;
    }
}
