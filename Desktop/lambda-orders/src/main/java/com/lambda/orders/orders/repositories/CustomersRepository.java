package com.lambda.orders.orders.repositories;
import com.lambda.orders.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
}
