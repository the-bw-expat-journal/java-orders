package com.lambda.orders.orders.repositories;

import com.lambda.orders.orders.models.Payment;
import org.springframework.data.repository.CrudRepository;


public interface PaymentRepository extends CrudRepository<Payment, Long> {


}
