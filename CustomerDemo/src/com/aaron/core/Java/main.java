package com.aaron.core.Java;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class main {

    public static void main(String args[]){

//        Customer customer = new Customer();
//
//        Map <String, Object> customer1 =  new HashMap<String,Object>();
//
//        customer1.put("reward","4099047461");
//
//        String response = JSONDTOTranslator.getJsonStringFromObject(customer1);
//
//        System.out.println(response);
//
//        try {
//            customer = JSONDTOTranslator.getEntityFromObject("{\"reward\": 4099047461}", Customer.class);
//            System.out.println("Worked " + customer.getReward());
//        }
//        catch(IOException ie) {
//            System.out.println("Io Exception " + ie.getMessage());
//        }

//        try{
//            String s = null;
//
//            s.equals("");
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }

        new Thread(()->{
            System.out.println("Hello World: "+Thread.currentThread().getName());

        }).start();


    }
}
