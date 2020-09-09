package com.lambda.orders.orders.controllers;

import com.lambda.orders.orders.models.Order;
import com.lambda.orders.orders.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService ordersServices;

    //    GET /orders/order/{id} - Returns the order and its customer with the given order number
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getOrderById(@PathVariable long id) {
        Order o = ordersServices.getById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    //    GET /orders/advanceamount - returns all orders with their customers that have an advanceamount greater than 0.
    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> getOrderByAdvanceAmount() {
        List<Order> ordList = ordersServices.getByAdvanceAmount();
        ordList.sort((o1, o2) -> (int) (o1.getAdvanceamount() - o2.getAdvanceamount()));
        return new ResponseEntity<>(ordList, HttpStatus.OK);
    }

    //    POST /orders/order - adds a new order to an existing customer
    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = ordersServices.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrdURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ordId}").buildAndExpand(newOrder.getOrdnum()).toUri();
        responseHeaders.setLocation(newOrdURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //    PUT /orders/order/{ordernum} - completely replaces the given order record
    @PutMapping(value = "/order/{id}", consumes = "application/json")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody Order updateOrd, @PathVariable long id) {
        updateOrd.setOrdnum(id);
        ordersServices.save(updateOrd);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    DELETE /orders/order/{ordername} - deletes the given order
    @DeleteMapping(value = "/order/{name}")
    public ResponseEntity<?> deleteOrderByName(@PathVariable String name) {
        ordersServices.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    DELETE /orders/order/{id} - deletes the given order
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrderByName(@PathVariable long id) {
        ordersServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
