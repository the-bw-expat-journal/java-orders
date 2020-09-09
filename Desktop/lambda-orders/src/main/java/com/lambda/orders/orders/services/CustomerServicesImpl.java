package com.lambda.orders.orders.services;


import com.lambda.orders.orders.models.Customer;
import com.lambda.orders.orders.models.Order;
import com.lambda.orders.orders.repositories.CustomersRepository;
import com.lambda.orders.orders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customersService")
public class CustomerServicesImpl implements CustomersServices {

    @Autowired
    private CustomersRepository customersrepo;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> custList = new ArrayList<>();

        customersrepo.findAll().iterator().forEachRemaining(custList::add);
        return custList;
    }

    @Override
    public Customer findById(Long custid) {
        Customer c = customersrepo.findById(custid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer %s does not exist.", custid)));
        return c;
    }

    @Override
    public List<Customer> findByLikeNames(String name) {
        return customersrepo.findByCustnameContainingIgnoringCase(name);
    }

    @Override
    public List<OrderCounts> getOrderCounts() {
        return customersrepo.getOrderCounts();
    }

    @Transactional
    @Override
    public void delete(long id) {
        customersrepo.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Customer %s not found.", id)));
        customersrepo.deleteById(id);
    }

    @Transactional
    @Override
    public Customer update(Customer customers, long id) {
        Customer currentCust = customersrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer %s not found.", id)));

        if (customers.getCustname() != null) {
            currentCust.setCustname(customers.getCustname());
        }
        if (customers.getCustcity() != null) {
            currentCust.setCustcity(customers.getCustcity());
        }
        if (customers.getCustcountry() != null) {
            currentCust.setCustcountry(customers.getCustcountry());
        }
        if (customers.getGrade() != null) {
            currentCust.setGrade(customers.getGrade());
        }
        if (customers.openingAmtHasValue) {
            currentCust.setOpeningamt(customers.getOpeningamt());
        }
        if (customers.outstandingAmtHasValue) {
            currentCust.setOutstandingamt(customers.getOutstandingamt());
        }
        if (customers.paymentAmtHasValue) {
            currentCust.setPaymentamt(customers.getPaymentamt());
        }
        if (customers.receiveAmtHasValue) {
            currentCust.setReceiveamt(customers.getReceiveamt());
        }
        if (customers.getWorkingarea() != null) {
            currentCust.setWorkingarea(customers.getWorkingarea());
        }
        if (customers.getAgent() != null) {
            currentCust.setAgent(customers.getAgent());
        }

        if (customers.getOrders().size() > 0) {
            currentCust.getOrders().clear();
            for (Order o : customers.getOrders()) {
                Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), currentCust, o.getOrderdescription());
                currentCust.getOrders().add(newOrder);
            }
        }

        return customersrepo.save(currentCust);
    }

    @Transactional
    @Override
    public Customer save(Customer customers) {
        Customer newCust = new Customer();

        if (customers.getCustcode() != 0) {
            customersrepo.findById(customers.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Customer %s not found.", customers.getCustcode())));

            newCust.setCustcode(customers.getCustcode());
        }

        newCust.setCustname(customers.getCustname());
        newCust.setCustcity(customers.getCustcity());
        newCust.setCustcountry(customers.getCustcountry());
        newCust.setGrade(customers.getGrade());
        newCust.setOpeningamt(customers.getOpeningamt());
        newCust.setOutstandingamt(customers.getOutstandingamt());
        newCust.setPaymentamt(customers.getPaymentamt());
        newCust.setReceiveamt(customers.getReceiveamt());
        newCust.setWorkingarea(customers.getWorkingarea());
        newCust.setAgent(customers.getAgent());

        newCust.getOrders().clear();
        for (Order o : customers.getOrders()) {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), newCust, o.getOrderdescription());
            newCust.getOrders().add(newOrder);
        }

        return customersrepo.save(newCust);
    }
}
