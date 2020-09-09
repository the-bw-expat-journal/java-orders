package com.lambda.orders.orders.services;
import com.lambda.orders.orders.models.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    Order getById(long id);

    List<Order> getByAdvanceAmount();

    void delete(String name);

    void deleteById(long id);
}