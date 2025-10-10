package com.aaron.core.java;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CorporateDemo {

    public static void main(String args[]){

        Map<String,Object> request = new HashMap<>();

        Map<String,Object> pickUpTimeMap = new HashMap<>();

        pickUpTimeMap.put("startsAt","2025-07-22T18:00:00");


        Map<String,Object> dropOffTimeMap = new HashMap<>();

        dropOffTimeMap.put("endsAt","2025-07-22T19:00:00");

        request.put("pickupTime", pickUpTimeMap);

        request.put("dropoffTime", dropOffTimeMap);


        System.out.println("Before: "+ request);

        new CorporateDemo().convertTime(request);

        System.out.println("After: "+ request);

    }
    public Map<String,Object> convertTime(Map<String,Object> request)
    {

        final String methodName = "convertTime";


        if(request.get("pickupTime") != null && request.get("dropoffTime") != null  ){

            Map<String,Object> pickUpTimeMap = (Map<String,Object>) request.get("pickupTime");

            Map<String,Object> dropOffTimeMap = (Map<String,Object>) request.get("dropoffTime");

            Long startsAt = convertTimeToMillis(pickUpTimeMap.get("startsAt").toString());

            Long endsAt = convertTimeToMillis(dropOffTimeMap.get("endsAt").toString());

            pickUpTimeMap.put("startsAt",startsAt);

            dropOffTimeMap.put("endsAt",endsAt);


        }else{

            System.out.println("Not eligible for conversion");

        }



        return request;
    }

    public Long convertTimeToMillis(String timeStamp)
    {

        final String methodName = "convertTimeToMillis";


        // Parse the timestamp string to a LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(timeStamp);

        // Convert to ZonedDateTime with system default time zone (or specify another ZoneId)
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        //ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        System.out.println(zonedDateTime);

        // Convert to milliseconds since epoch
        long millis = zonedDateTime.toInstant().toEpochMilli();

        //System.out.println("Milliseconds: " + millis);

        return millis;
    }
}


