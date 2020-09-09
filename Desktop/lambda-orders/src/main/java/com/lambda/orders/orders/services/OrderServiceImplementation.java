package com.lambda.orders.orders.services;


import com.lambda.orders.orders.models.Order;
import com.lambda.orders.orders.models.Payment;
import com.lambda.orders.orders.models.Customer;

import com.lambda.orders.orders.repositories.OrdersRepository;
import com.lambda.orders.orders.repositories.CustomersRepository;
import com.lambda.orders.orders.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Transactional
@Service(value = "ordersService")
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrdersRepository ordersrepo;

    @Autowired
    private PaymentRepository paymentsrepos;

    @Autowired
    private CustomersRepository customersrepo;

    @Transactional
    @Override
    public Order save(Order orders) {
        Order newOrd = new Order();

        if (orders.getOrdnum() != 0) {
            ordersrepo.findById(orders.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Order %s not found.", orders.getOrdnum())));

            newOrd.setOrdnum(orders.getOrdnum());
        }

        newOrd.setAdvanceamount(orders.getAdvanceamount());
        newOrd.setOrdamount(orders.getOrdamount());
        newOrd.setOrderdescription(orders.getOrderdescription());

        newOrd.setCustomer(customersrepo.findById(orders.getCustomer().getCustcode())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer %s not found.", orders.getCustomer().getCustcode()))));

        newOrd.getPayments().clear();
        for (Payment p : orders.getPayments()) {
            Payment newPay = paymentsrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Payment %s not found.", p.getPaymentid())));
            newOrd.getPayments().add(newPay);
        }

        return ordersrepo.save(newOrd);
    }

    @Override
    public Order getById(long id) {
        Order o = ordersrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order %s does not exist.", id)));
        return o;
    }

    @Override
    public List<Order> getByAdvanceAmount() {
        return ordersrepo.findByAdvanceamountGreaterThan(0.0);
    }

    @Transactional
    @Override
    public void delete(String name) {
        ordersrepo.deleteByOrderdescriptionIgnoringCase(name);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        ordersrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order %s does not exist.", id)));
        ordersrepo.deleteById(id);
    }
}
