package com.lambda.orders.orders.services;

import com.lambda.orders.orders.models.Customer;
import com.lambda.orders.orders.views.OrderCounts;

import java.util.List;

public interface CustomersServices {
    Customer save(Customer customers);

    List<Customer> findAllCustomers();

    Customer findById(Long custid);

    List<Customer> findByLikeNames(String name);

    List<OrderCounts> getOrderCounts();

    void delete(long id);

    Customer update(Customer customers, long id);
}
