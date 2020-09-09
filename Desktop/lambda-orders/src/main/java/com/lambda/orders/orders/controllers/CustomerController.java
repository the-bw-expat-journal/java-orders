package com.lambda.orders.orders.controllers;
import com.lambda.orders.orders.models.Customer;
import com.lambda.orders.orders.services.CustomersServices;
import com.lambda.orders.orders.views.OrderCounts;
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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomersServices customersServices;

    //    GET /customers/orders - Returns all customers with their orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> getAllCustomersAndOrders() {
        List<Customer> custList = customersServices.findAllCustomers();
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }

    //    GET /customers/customer/{id} - Returns the customer and their orders with the given customer id
    @GetMapping(value = "/customer/{custid}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long custid) {
        Customer c = customersServices.findById(custid);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //    GET /customers/namelike/{likename} - Returns all customers and their orders with a customer name containing the given substring
    @GetMapping(value = "/namelike/{likenames}", produces = {"application/json"})
    public ResponseEntity<?> getByLikeNames(@PathVariable String likenames) {
        List<Customer> custList = customersServices.findByLikeNames(likenames);
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }

    //    GET /customers/orders/count - Using a custom query, return a list of all customers with the number of orders they have placed.
    @GetMapping(value = "/orders/count", produces = {"application/json"})
    public ResponseEntity<?> getByOrderCount() {
        List<OrderCounts> custList = customersServices.getOrderCounts();
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }

    //    POST /customers/customer - Adds a new customer including any new orders
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCust) {
        newCust.setCustcode(0);
        newCust = customersServices.save(newCust);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCust.getCustcode()).toUri();
        responseHeaders.setLocation(newCustURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //    PUT /customers/customer/{custcode} - completely replaces the customer record including associated orders with the provided data
    @PutMapping(value = "/customer/{id}", consumes = "application/json")
    public ResponseEntity<?> replaceCustomer(@Valid @RequestBody Customer updateCust, @PathVariable long id) {
        updateCust.setCustcode(id);
        customersServices.save(updateCust);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    PATCH /customers/customer/{custcode} - updates customers with the new data. Only the new data is to be sent from the frontend client.
    @PatchMapping(value = "/customer/{custid}", consumes = "application/json")
    public ResponseEntity<?> updatePartCustomer(@Valid @RequestBody Customer updateCust, @PathVariable long custid) {
        customersServices.update(updateCust, custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    DELETE /customers/customer/{custcode} - Deletes the given customer including any associated orders
    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
        customersServices.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

