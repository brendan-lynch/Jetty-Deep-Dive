package com.shop.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;

/**
 * Created by brendan on 7/9/15.
 */

@Path("/customers")
public interface CustomerResource {

    @GET
    @Path("{id}")
    @Produces("application/xml")
    StreamingOutput getCustomer(@PathParam("id") int id);



    @POST
    @Consumes("application/xml")
    Response createCustomer(InputStream is);

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    void updateCustomer(@PathParam("id") int id, InputStream input);

}
