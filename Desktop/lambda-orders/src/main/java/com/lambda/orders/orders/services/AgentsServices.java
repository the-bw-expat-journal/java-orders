package com.lambda.orders.orders.services;

import com.lambda.orders.orders.models.Agent;

import java.util.List;

public interface AgentsServices {
    Agent save(Agent agents);

    Agent getById(Long id);

    void deleteUnassigned(long id);

    List<Agent> getUnassigned();
}
