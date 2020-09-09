package com.lambda.orders.orders.repositories;

import com.lambda.orders.orders.models.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Long> {
    List<Order> findByAdvanceamountGreaterThan(double amount);

    void deleteByOrderdescriptionIgnoringCase(String name);

}
