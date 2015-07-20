package com.shop.services;

import com.shop.domain.Customer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by brendan on 7/18/15.
 */
public class CustomerResourceService implements CustomerResource {
    //stateful object, without a database this object will hold customers in memory.

    @Override
    public StreamingOutput getCustomer(int id) {
        final Customer customer = customerDB.get(id);
        if(customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return outputStream -> Customer.outputCustomer(outputStream, customer);
    }

    @Override
    public Response createCustomer(InputStream input) {
        Customer customer = Customer.readCustomer(input);
        customer.setId(idGenerator.getAndIncrement());
        customerDB.put(customer.getId(), customer);

        return Response.created(URI.create("/customers/" + customer.getId())).build();
    }

    @Override
    public void updateCustomer(int id, InputStream input) {
        Customer customer = Customer.readCustomer(input);
        customer.setId(id);
        customerDB.put(id, customer);
    }

    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger();
}
