package com.shop.services;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by brendan on 7/9/15.
 */
public class ShoppingApplication extends Application {

    private Set<Object> singletons = new HashSet<>();

    public ShoppingApplication(){
        singletons.add(new CustomerResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
