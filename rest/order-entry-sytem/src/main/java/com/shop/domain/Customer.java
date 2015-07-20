package com.shop.domain;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

/**
 * Created by brendan on 7/9/15.
 */
public class Customer {
    //Java Bean for customer, POJO for now

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static void outputCustomer(OutputStream output, Customer customer) {
        PrintStream xmlCustomer = new PrintStream(output);

        xmlCustomer.println("<customer>");
        xmlCustomer.println("    <id>" + customer.getId() + "</id>");
        xmlCustomer.println("    <firstName>" + customer.getFirstName() + "</firstName>");
        xmlCustomer.println("    <lastName>" + customer.getLastName() + "</lastName>");
        xmlCustomer.println("    <street>" + customer.getStreet() + "</street>");
        xmlCustomer.println("    <city>" + customer.getCity() + "</city>");
        xmlCustomer.println("    <state>" + customer.getState() + "</state>");
        xmlCustomer.println("    <zip>" + customer.getZip() + "</zip>");
        xmlCustomer.println("    <country>" + customer.getCountry() + "</country>");
        xmlCustomer.println("</customer>");
    }

    public static Customer readCustomer(InputStream input) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(input);
            Element root = document.getDocumentElement();

            Customer customer = new Customer();

            NodeList nodes = root.getChildNodes();
           // Field[] fields = Customer.class.getDeclaredFields();
            //Class clazz = Customer.class;
            //TODO: Dynamically generate the methods to compare so that there is a single
            //source of truth between the class members and the list being iterated over.
            for(int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                if(element.getTagName().equals("id")) {
                    customer.setId(Integer.valueOf(element.getTextContent()));
                }
                if(element.getTagName().equals("firstName")) {
                    customer.setFirstName(element.getTextContent());
                }
                else if(element.getTagName().equals("lastName")) {
                    customer.setLastName(element.getTextContent());
                }
                else if(element.getTagName().equals("street")) {
                    customer.setStreet(element.getTextContent());
                }
                else if(element.getTagName().equals("city")) {
                    customer.setCity(element.getTextContent());
                }
                else if(element.getTagName().equals("state")) {
                    customer.setState(element.getTextContent());
                }
                else if(element.getTagName().equals("zip")) {
                    customer.setZip(element.getTextContent());
                }
                else if(element.getTagName().equals("USA")) {
                    customer.setCountry(element.getTextContent());
                }
            }
          /*  for(int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                for(int j = 0; j < fields.length; j++) {
                    if(element.getTagName().equals(fields[j].getName())) {
                        if(element.getTagName().equals("id")) {
                            customer.setId(Integer.valueOf(element.getTextContent()));
                        }
                        else {
                            clazz.getMethod("set" + fields[j].getName()).invoke(customer, element.getTextContent());
                        }
                    }
                }
            }*/

            return customer;
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }
    }

    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
