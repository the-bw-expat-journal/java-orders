package com.java.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import com.java.orders.models.Order;
public interface OrdersRepository extends CrudRepository<Order, Long> {
}
