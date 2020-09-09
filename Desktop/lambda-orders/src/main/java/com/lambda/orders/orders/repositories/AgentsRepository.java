package com.lambda.orders.orders.repositories;
import com.lambda.orders.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AgentsRepository extends CrudRepository<Agent, Long> {

    List<Agent>findByCustomers_Empty();
}
