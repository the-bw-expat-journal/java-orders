package com.lambda.orders.orders.repositories;

import com.lambda.orders.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Long> {
}
