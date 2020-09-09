package com.lambda.orders.orders.repositories;
import com.lambda.orders.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long> {
}
