package com.lambda.orders.orders.repositories;
import com.lambda.orders.orders.models.Customer;
import com.lambda.orders.orders.views.OrderCounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByCustnameContainingIgnoringCase(String custname);

    @Query(value = "SELECT c.custname as custname, count(o.ordnum) as countorders " +
            "FROM customers c LEFT JOIN orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY countorders desc", nativeQuery = true)
    List<OrderCounts> getOrderCounts();
}



