package com.java.orders.repositories;
import com.java.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
}
