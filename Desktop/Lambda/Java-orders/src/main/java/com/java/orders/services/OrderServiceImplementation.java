package com.java.orders.services;


import com.java.orders.models.Order;
import com.java.orders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderServiceImplementation implements OrderService {

    @Autowired
    OrdersRepository orderrepos;

    @Override
    public Order save(Order order) {
        return orderrepos.save(order);
    }
}
