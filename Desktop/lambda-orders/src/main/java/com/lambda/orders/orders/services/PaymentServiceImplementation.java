package com.lambda.orders.orders.services;


import com.lambda.orders.orders.models.Payment;
import com.lambda.orders.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service( value = "paymentService")
public class PaymentServiceImplementation implements PaymentService{

    @Autowired
    PaymentRepository paymentrepos;

    @Override
    public Payment save(Payment payment) {
        return paymentrepos.save(payment);
    }
}
