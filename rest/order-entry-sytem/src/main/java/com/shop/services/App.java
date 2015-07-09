package com.shop.services;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

/**
 * Created by brendan on 7/9/15.
 */
public class App {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyHandler = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyHandler.setInitOrder(0);
        jerseyHandler.setInitParameter("javax.ws.rs.Application", ShoppingApplication.class.getCanonicalName());

        jettyServer.start();
        jettyServer.join();
    }
}
