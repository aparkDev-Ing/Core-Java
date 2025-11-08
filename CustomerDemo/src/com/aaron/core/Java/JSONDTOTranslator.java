//package com.aaron.core.Java;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * This utility class converts the object to JSON string format and vice versa
// *
// * @author msridharaital
// * @date Mat 30, 2018
// */
//public class JSONDTOTranslator
//{
//
//    private JSONDTOTranslator()
//    {
//    }
//
//    public static <T> String getJsonString(T t)
//    {
//        if (t != null)
//        {
//            ObjectMapper mapperObj = new ObjectMapper();
//            try
//            {
//                return mapperObj.writeValueAsString(t);
//            }
//            catch (IOException e)
//            {
//                System.out.println("Exception while converting the object to json string "+ e);
//            }
//        }
//        return null;
//    }
//
//    public static <T> String getJsonStringFromObject(T obj)
//    {
//        return new GsonBuilder().create().toJson(obj);
//    }
//
//    public static <T> Object getObjectFromJsonString(String jsonString, Class<T> cls)
//    {
//        Gson gson = new Gson();
//        T t = gson.fromJson(jsonString, cls);
//        return t;
//    }
//
//    public static <T> T mapObjectToType(Object obj, Class<T> tClass)
//    {
//        ObjectMapper mapper = new ObjectMapper();
//        T pojo = mapper.convertValue(obj, tClass);
//        return pojo;
//    }
//
//    public static <T> T getEntityFromObject(String message, Class<T> tClass) throws IOException
//    {
//        ObjectMapper mapper = new ObjectMapper();
//        T entity = mapper.readValue(message, tClass);
//        return entity;
//    }
//
//    public static <T> String getJsonFromObject(T t) throws JsonProcessingException
//    {
//        ObjectMapper mapperObj = new ObjectMapper();
//        return mapperObj.writeValueAsString(t);
//    }
//
//    public static <T> T getData(Class<T> clazz, T data) throws IOException
//    {
//        ObjectMapper mapper = getObjectMapper();
//
//        String json = mapper.writeValueAsString(data);
//        System.out.println("getData"+ "() -> Base Response Json: " + json);
//
//        return mapper.readValue(json, clazz);
//    }
//
//    public static <T> List<T> getDataList(Class<T[]> clazz, List<LinkedHashMap> mapList) throws IOException
//    {
//        ObjectMapper mapper = getObjectMapper();
//
//        String json = mapper.writeValueAsString(mapList);
//        System.out.println("getDataList"+ "() -> Base Response Json: " + json);
//
//        T[] objectArray = mapper.readValue(json, clazz);
//
//        return Arrays.asList(objectArray);
//    }
//
//    private static ObjectMapper getObjectMapper()
//    {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        return mapper;
//    }
//
//}