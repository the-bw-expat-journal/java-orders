package com.lambda.orders.orders.services;

import com.lambda.orders.orders.models.Agent;
import com.lambda.orders.orders.models.Customer;
import com.lambda.orders.orders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service(value = "agentsServices")
public class AgentsServicesImpl implements AgentsServices {

    @Autowired
    private AgentsRepository agentsrepo;

    @Transactional

    @Override
    public Agent save(Agent agents) {
        return agentsrepo.save(agents);
    }

    @Override
    public Agent getById(Long id) {
        Agent a = agentsrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Agent %l does not exist.", id)));
        return a;
    }

    @Transactional
    @Override
    public void deleteUnassigned(long id) {
        List<Agent> agentList = agentsrepo.findByCustomers_Empty();

        for (Agent a : agentList) {
            if(a.getAgentcode() == id) {
                agentsrepo.delete(a);
            }
        }
    }

    @Override
    public List<Agent> getUnassigned() {
        return agentsrepo.findByCustomers_Empty();
    }
}
