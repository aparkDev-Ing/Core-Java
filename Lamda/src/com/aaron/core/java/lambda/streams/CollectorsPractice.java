package com.aaron.core.java.lambda.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorsPractice<T> {


    public static void main(String args[]){

        CollectorsPractice collectorsPractice = new CollectorsPractice();

        // Convert Stream to Collections (List in this case)
        List<Fighter> fighterList = Arrays.asList(new Fighter("Aaron", 15, 5), new Fighter("Jon Jones", 19, 1));

        Stream<Integer>  fighterIntegerStream=   mapToNoOfWinsInteger(fighterList.stream()) ;

        List<Integer> fighterIntegerList = collectorsPractice.returnList(fighterIntegerStream);

        fighterIntegerList.forEach(System.out::println);

        //IntStream fighterIntStream = mapToNoOfWins(fighterList.stream());
        //List<Integer> winList =  fighterIntStream.collect(Fighter::getWins);

        // Convert Stream to specific Collections (ArrayList in this case)
        fighterIntegerStream=   mapToNoOfWinsInteger(fighterList.stream()) ;
        Collection<Integer> fighterArrayList = collectorsPractice.returnArrayListCollection(fighterIntegerStream);
        fighterArrayList.forEach(System.out::println);

        //Convert Stream to Map
        Stream<Fighter> fighterStream=   fighterList.stream() ;

        Map<String, Fighter>  fighterMap =  fighterStream.collect(Collectors.toMap(f -> f.getName(), f -> f ));

        fighterMap.forEach((k,v) -> System.out.println("Key "+k + " Value: "+ v));


        //Convert Stream to Array
        fighterStream=   fighterList.stream() ;
        Fighter[] fighterArray = fighterStream.toArray(Fighter[]::new);


        //counting
        fighterStream=   fighterList.stream() ;
        // 똑같음, 하지만 grouping 할때 유용 Long count = fighterStream.count();
        Long count = fighterStream.collect(Collectors.counting());
        System.out.println("Count: "+ count);

        //Sum
        fighterStream=   fighterList.stream() ;

        //int totalWins = fighterStream.mapToInt(Fighter::getWins).sum();

        //int totalWins = fighterStream.mapToInt(Fighter::getWins).reduce(0, (a,b) -> a + b);

        int totalWins = fighterStream.collect(Collectors.summingInt(Fighter::getWins));
        System.out.println("Total Wins(SummingInt): "+ totalWins);


        //Sum using mapper in reducing method
        fighterStream=   fighterList.stream() ;

        int totalwinsUsingMapper = fighterStream.collect(Collectors.reducing(0, Fighter::getWins , (a,b) -> a+ b));
        System.out.println("Total Wins(Mapper): "+totalwinsUsingMapper);


        //Max
        IntStream intStream = new Random().ints(1,46).limit(6);

        int[] intArray = {14,22,36,46,59};

        IntStream intStream1 = Arrays.stream(intArray);

        OptionalInt max = intStream1.max();

        System.out.println("Max number(using max method) : "+ max.orElse(0));

        //OptionalInt max = intStream.reduce(Integer::max);

        //Optional<Integer> max2 = intStream2.boxed().collect(Collectors.reducing(Integer::max));

        intStream1 = Arrays.stream(intArray);
        
        Optional<Integer> max2 = intStream1.boxed().collect(Collectors.reducing( (a,b) -> Integer.max(a,b)));

        System.out.println("Max number(using reducing): "+ max2.orElse(0));


        //Joining
        fighterStream = fighterList.stream() ;

        String ufcRoaster = fighterStream.map(Fighter::getName).collect(Collectors.joining(", "));

        System.out.println(ufcRoaster);



    }

    public List<T> returnList(Stream<T> stream){

        return stream.collect(Collectors.toList());
    }

    public Collection<T> returnArrayListCollection(Stream<T> stream){

        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<T> returnIntStreamCollections(Stream<T> stream){

        return stream.collect(Collectors.toList());
    }

    public static Stream<Integer> mapToNoOfWinsInteger(Stream<Fighter> fighterStream){

        return fighterStream.map(Fighter::getWins);
    }
    public static IntStream mapToNoOfWins(Stream<Fighter> fighterStream){

        return fighterStream.mapToInt(Fighter::getWins);
    }

}

 class Fighter{

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getWins() {
         return wins;
     }

     public void setWins(int wins) {
         this.wins = wins;
     }

     public int getLoses() {
         return loses;
     }

     public void setLoses(int loses) {
         this.loses = loses;
     }

     public Fighter(String name, int wins, int loses) {
         this.name = name;
         this.wins = wins;
         this.loses = loses;
     }

     @Override
     public String toString() {
         return "Fighter{" +
                 "name='" + name + '\'' +
                 ", wins=" + wins +
                 ", loses=" + loses +
                 '}';
     }

     String name;

    int wins;

    int loses;




}
