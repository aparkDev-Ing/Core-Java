//package com.aaron.core.Java;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.util.*;
//
//
//@JsonPropertyOrder({ "name","reward"})
//public class Customer {
//
//    @JsonProperty("name")
//    private String name;
//
//    @JsonIgnore
//    @JsonProperty("reward")
//    private BigInteger reward;
//
//
//    public Customer(){
//
//    }
//
//
//    @JsonAnyGetter
//    public String getName(){
//
//        return this.name;
//    }
//
//    @JsonAnyGetter
//    public void setName(String name){
//
//        this.name = name;
//    }
//
//    @JsonAnyGetter
//    public BigInteger getReward(){
//
//        return this.reward;
//    }
//
//    @JsonAnyGetter
//    public void setReward(BigInteger reward){
//
//        this.reward = reward;
//    }
//}
