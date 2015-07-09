package com.shop.services;

import com.shop.domain.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by brendan on 7/9/15.
 */

@Path("/customers")
public class CustomerResource {
    //stateful object, without a database this object will hold customers in memory.

    @GET
    public String getCustomer(){
        return "Hello World";
    }
    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger();

}
