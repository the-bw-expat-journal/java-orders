package com.java.orders.services;


import com.java.orders.models.Payment;
import com.java.orders.repositories.PaymentRepository;
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