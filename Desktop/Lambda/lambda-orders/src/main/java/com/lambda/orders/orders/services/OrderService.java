package com.lambda.orders.orders.services;
import com.lambda.orders.orders.models.Order;

public interface OrderService {

    Order save(Order order);
}