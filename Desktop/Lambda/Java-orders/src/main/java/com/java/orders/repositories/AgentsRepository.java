package com.java.orders.repositories;
import com.java.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long> {
}
