package com.lambda.orders.orders.controllers;

import com.lambda.orders.orders.models.Agent;
import com.lambda.orders.orders.services.AgentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentsServices agentsServices;

    //
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> getAgentByID(@PathVariable long id) {
        Agent a = agentsServices.getById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }


    @DeleteMapping(value = "/unassigned/{id}")
    public ResponseEntity<?> deleteUnassignedAgents(@PathVariable long id) {
        agentsServices.deleteUnassigned(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/unassigned", produces = {"application/json"})
    public ResponseEntity<?> getUnassignedAgents() {
        List<Agent> agentList = agentsServices.getUnassigned();
        return new ResponseEntity<>(agentList, HttpStatus.OK);
    }
}
