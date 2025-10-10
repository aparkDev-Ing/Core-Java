package com.aaron.core.java.lambda.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGroupingPractice {

    public static void main(String args[]){


       // printTodayDataPartitioningBy();

        printTodayDataGroupingBy();


    }


//    static void printTodayDataPartitioningBy(){
//
//
//        List<Store> storeList = Arrays.asList(new Store(false,"Jaeyoung"),new Store(false,"Jaeyoung"),new Store(false,"Jaeyoung"),new Store(false,"Jaeyoung"),new Store(false,"Jaeyoung"), new Store(true,"Aaron"), new Store(true,"Aaron"),new Store(true,"Aaron"),new Store(true,"Aaron"),new Store(true,"Aaron"));
//
//        //Store list by manager
//        Stream<Store> storeStream = storeList.stream();
//
//        Map<Boolean, List<Store>> storeMap = storeStream.collect(Collectors.partitioningBy(Store::isManagerAaron ));
//
//        System.out.println("Jaeyoung's store list: "+  storeMap.get(false) );
//        System.out.println("Aaron's store list: "+  storeMap.get(true) );
//
//        System.out.println("");
//
//        //Store list by manager and by performance
//        storeStream = storeList.stream();
//
//        Map<Boolean , Map<Boolean, List<Store>>> storeMapByNoOfSales = storeStream.collect(Collectors.partitioningBy(Store::isManagerAaron, Collectors.partitioningBy(s -> s.salesToday > 50)));
//
//        System.out.println("Jaeyoung's store list with Sales > 50 : "+  storeMapByNoOfSales.get(false).get(true) );
//        System.out.println("Aaron's store list with Sales > 50: "+  storeMapByNoOfSales.get(true).get(true) );
//
//        System.out.println("");
//
//
//        //Store list by manager and by performance by count
//        storeStream = storeList.stream();
//
//        Map<Boolean, Map<Boolean, Long>> noOfStoresMapByPerformance = storeStream.collect(Collectors.partitioningBy(Store::isManagerAaron, Collectors.partitioningBy(s -> s.salesToday > 50, Collectors.counting())));
//
//        System.out.println("Jaeyoung's number of stores with Sales > 50 : "+  noOfStoresMapByPerformance.get(false).get(true) );
//        System.out.println("Aaron's number of stores with Sales > 50 : "+  noOfStoresMapByPerformance.get(true).get(true) );
//
//        System.out.println("");
//
//
//
//        //Best store by performance by manager (optional)
//        storeStream = storeList.stream();
//
//        Map<Boolean,Optional<Store>> storePerformanceMapByManagerOptional = storeStream.collect(Collectors.partitioningBy(Store::isManagerAaron, Collectors.maxBy(Comparator.comparingInt(Store::getSalesToday))));
//
//        System.out.println("Jaeyoung's Best Store (optional): "+  storePerformanceMapByManagerOptional.get(false).orElseGet( ()-> new Store()) );
//        System.out.println("Aaron's Best store (optional): "+  storePerformanceMapByManagerOptional.get(true).orElseGet( ()-> new Store()) );
//
//        System.out.println("");
//
//        //Best store by performance by manager (non-optional)
//        storeStream = storeList.stream();
//
//        Map<Boolean,Store> storePerformanceMapByManager = storeStream.collect(Collectors.partitioningBy(Store::isManagerAaron, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Store::getSalesToday)), Optional::get )));
//
//        System.out.println("Jaeyoung's Best Store : "+  storePerformanceMapByManager.get(false));
//        System.out.println("Aaron's Best store: "+  storePerformanceMapByManager.get(true) );
//
//        System.out.println("");
//
//
//        //Stores dvided into managers and sorted by performance
//        storeStream = storeList.stream();
//
//        Map<Boolean,List<Store>> storeByMangerandPerformance = storeStream.sorted(Comparator.comparingInt(Store::getSalesToday).reversed()).collect(Collectors.partitioningBy(Store::isManagerAaron));
//        //Map<Boolean,List<Store>> storeByMangerandPerformance = storeStream.collect(Collectors.partitioningBy(Store::isManagerAaron), ));
//
//        System.out.println("Jaeyoung's stores by performance : "+  storeByMangerandPerformance.get(false));
//        System.out.println("Aaron's stores by performance: "+  storeByMangerandPerformance.get(true) );
//
//        System.out.println("");
//
//
//    }



    static void printTodayDataGroupingBy(){

        List<Store> storeList = Arrays.asList(
                new Store(false,"Aaron", cuisine.Mexican.value), new Store(false,"Aaron", cuisine.American.value),new Store(false,"Aaron", cuisine.Asian.value),
                new Store(false,"Aaron", cuisine.Mexican.value), new Store(false,"Aaron", cuisine.American.value),new Store(false,"Aaron", cuisine.Asian.value),
                new Store(false,"Hongsun", cuisine.Mexican.value), new Store(false,"Hongsun", cuisine.American.value),new Store(false,"Hongsun", cuisine.Asian.value),
                new Store(false,"Hongsun", cuisine.Mexican.value), new Store(false,"Hongsun", cuisine.American.value),new Store(false,"Hongsun", cuisine.Asian.value),
                new Store(false,"Jaeyoung", cuisine.Mexican.value), new Store(false,"Jaeyoung", cuisine.American.value),new Store(false,"Jaeyoung", cuisine.Asian.value),
                new Store(false,"Jaeyoung", cuisine.Mexican.value), new Store(false,"Jaeyoung", cuisine.American.value),new Store(false,"Jaeyoung", cuisine.Asian.value)

        );


        Stream<Store> storeStream = storeList.stream();

        //GroupBy manager
        Map<String,List<Store>> managerStoreList = storeStream.collect(Collectors.groupingBy(Store::getManagerName, Collectors.toList()));
        System.out.println("Store List by managers: " + managerStoreList);


        //GroupBy manager and display best store
        storeStream = storeList.stream();
        Map<String, Store> managerBestStoreMap = storeStream.collect(Collectors.groupingBy(Store::getManagerName,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Store::getSalesToday)), s -> s.orElseGet(()-> new Store()) )));
        //Map<String, Optional<Store>> managerBestStoreMap = storeStream.collect(Collectors.groupingBy(Store::getManagerName, Collectors.maxBy(Comparator.comparingInt(Store::getSalesToday))));
        //Map<String, Store> managerBestStoreMap = storeStream.collect(Collectors.groupingBy(Store::getManagerName, Collectors.maxBy(Comparator.comparingInt(Store::getSalesToday))));
        System.out.println("Best Store per manager by performance: " + managerBestStoreMap);


        //GroupBy manager and cuisine
        storeStream = storeList.stream();
        Map<String,Map<String,List<Store>>> managerStoreByCuisineMap = storeStream.collect(Collectors.groupingBy(Store::getManagerName,Collectors.groupingBy(Store::getCuisine, Collectors.toList())));
        System.out.println("StoreList by manager and cuisine: " + managerStoreByCuisineMap);

        //GroupBy managerName-StoreId-Cusine Comb and map to set of Great, okay, bad
        storeStream = storeList.stream();
        Map<String, Set<grade>> gradeStoreMap = storeStream.collect(Collectors.groupingBy(k-> k.getManagerName() + k.getStoreId() + k.getCuisine(),
                Collectors.mapping( v ->
                {
                    if(v.getSalesToday() >= 60){
                        return grade.Great;
                    }
                    else if(v.getSalesToday() >= 40){
                            return grade.Okay;
                        }
                    else{
                        return grade.Bad;
                    }
                },
                        Collectors.toSet()

        )

        )

        );

        System.out.println("StoreList by manager divided into grade: " + gradeStoreMap);

    }


}

class Store{

    @Override
    public String toString() {
        return "Store{" +
                "cuisine='" + cuisine + '\'' +
                ", managerName='" + managerName + '\'' +
                ", storeId='" + storeId + '\'' +
                ", salesToday=" + salesToday +
                '}';
    }

    public Store(boolean isManagerAaron, String managerName, String cuisine) {
        this.isManagerAaron = isManagerAaron;
        this.managerName = managerName;
        this.storeId =  UUID.randomUUID().toString().substring(0,5);
        this.salesToday = new Random().nextInt(101);
        this.cuisine=cuisine;
    }

    public Store(){

    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    String cuisine;

    boolean isManagerAaron;
    String managerName;

    String storeId;

    int salesToday;

    public boolean isManagerAaron() {
        return isManagerAaron;
    }

    public void setManagerAaron(boolean managerAaron) {
        isManagerAaron = managerAaron;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getSalesToday() {
        return salesToday;
    }

    public void setSalesToday(int salesToday) {
        this.salesToday = salesToday;
    }




}


enum cuisine{
    Mexican("Mexican","Mexican"),
    American("American","American"),
    Asian("Asian","Asian");

    String key;
    String value;


    cuisine(String key,  String value){
        this.key=key;
        this.value=value;
    }


}

enum grade{
    Great("Labeled as Great -> Sales > 60 per day"),
    Okay("Labeled as Okay -> Sales > 40 per day"),
    Bad("Labeled as Bad -> Sales < 20 per day");

    String description;
    grade(String description)
    {
     this.description = description ;
    }

    String getDescription(){
        return this.description;
    }
}