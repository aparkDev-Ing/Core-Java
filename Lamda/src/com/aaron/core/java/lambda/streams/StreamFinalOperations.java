package com.aaron.core.java.lambda.streams;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFinalOperations<T> {

    public long parellelsum(long n){

        //return Stream.iterate(1L, i -> i+1).limit(8).parallel().reduce(0L,Long::sum);

        //return Stream.iterate(1L, i -> i+1).limit(8).parallel().reduce(0L, (a,b) -> Long.sum(a,b));

        return Stream.iterate(1L, i -> i+1).limit(8).parallel().reduce(0L, (a,b) -> Long.sum(a,b));

        //return Stream.iterate(1L, i -> i+1).limit(8).parallel().reduce(0L, (a,b) -> a+b);
    }

//    public static void main(String args[]){
//
//        System.out.println(new StreamFinalOperations().parellelsum(8));
//    }

    public static void main(String args[]){

        StreamFinalOperations streamFinalOperations = new StreamFinalOperations();


        //#foreach and foreachordered

        // 순차스트림, 순서대로 잘프린트된다
        streamFinalOperations.printFinalForEach(streamFinalOperations.returnStream("Hello", "Aaron","John","Kelly","David"));

        //병렬스트림, 순서대로 프린트가안된다 - 스트림을 이미소모햇기에 새로운 스트림오브젝트 생성**

        System.out.println("------------------------------------------");

        Stream<String> stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

        stringStream.forEachOrdered(System.out::println);

        //stringStream.forEach(System.out::println);

        //Stream<String> stringStream2 = streamFinalOperations.<String>returnStream("Hello", "Aaron","John","Kelly","David").parallel();


        //#Anymatch, nonematch, allmatch

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

         stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

         System.out.println(streamFinalOperations.stringReturnNoneMatch(stringStream));

         stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

         System.out.println(streamFinalOperations.stringReturnAllMatch(stringStream));

         stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

         System.out.println(streamFinalOperations.stringReturnAnyMatch(stringStream));


        //#findFirst, findAny

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

        System.out.println("First element in array with length 5: " +streamFinalOperations.findFirst(stringStream).orElseGet(()->"Could not find"));

        stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

        //findany의경우 parellel 이아니면 findFirst와 결과값이 항상같은듯하다 왜냐면 생각해보면 퍼포먼스를생각해서 맨처음 인덱스에잇는데 굳이 랜덤으로 할이유는없기때문
        System.out.println("Any element in array with length 5: " +streamFinalOperations.findAny(stringStream).orElseGet(()->"Could not find"));


        //#mapToInt

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        //stringStream = Stream.of("Hello", "Aaron","John","Kelly","David").parallel();

        stringStream = Stream.of("Hel", "Aaron","John","Kelly","David");

        //이렇게도 가능하지만, mapToInt를써서 기본형으로바꾸고 퍼포먼스/성능 을 올  린다
        //Stream<Integer> integerStream = stringStream.map(Integer::parseInt);

        IntStream intStream1 = stringStream.mapToInt(String::length);

        stringStream = Stream.of("Hello", "Aaron","John","Kelly","David");
        IntStream intStream2 = stringStream.mapToInt(String::length);

        stringStream = Stream.of("Hello", "Aaron","John","Kelly","David");

        IntStream intStream3 = stringStream.mapToInt(String::length);

        stringStream = Stream.of("Hello", "Aaron","John","Kelly","David");
        IntStream intStream4 = stringStream.mapToInt(String::length);

        //int count = intStream1.reduce(0,  (a,b)-> a + 1);
        int count = intStream1.reduce( 0,(a,b)-> a + 1);

         //empty stream with identity
         //int count = IntStream.empty().reduce(0,  (a,b)-> a + 1);

//        int sum = intStream2.reduce(0,  (a,b)-> a + b);

//        int min = intStream3.reduce(Integer.MAX_VALUE,  (a,b)-> a < b ? a : b);

//        int max = intStream4.reduce(Integer.MIN_VALUE,  (a,b)-> a > b ? a : b);

        //Long count = intStream1.count();

        int sum = intStream2.sum();

        //OptionalInt min = OptionalInt.empty();


        //OptionalInt min = intStream3.reduce(Integer::min);

        //람다로바꾼거
        OptionalInt min = intStream3.reduce((a,b)->Integer.min(a,b));

        //empty stream without identity  //이렇게 초기화값이 없는데 empty 스트림이면, Empty OptionalInt 를반환한다
        //OptionalInt min = IntStream.empty().reduce(Integer::min);
        //System.out.println(min);

        OptionalInt max = intStream4.reduce(Integer::max);

        //OptionalInt min = intStream3.min();

        //OptionalInt max = intStream4.max();

        System.out.println("count: "+ count);

        System.out.println("sum: "+ sum);

        //no such element
        //System.out.println("min: "+ min.getAsInt());
        System.out.println("min: "+ min.orElseGet(()-> 4));

        System.out.println("max: "+ max.getAsInt());

    }


//    public Stream<T> returnStream(T[] data){
//
//        List<T> tList = Arrays.asList(data);
//
//        return tList.stream();
//    }
    public Stream<T> returnStream(T... data){

        List<T> tList = Arrays.asList(data);

        return tList.<T>stream();
    }

    public void printFinalForEach(Stream<T> streamData){

        streamData.forEach(System.out::println);
    }
    public void printFinalForEachOrdered(Stream<T> streamData){

        streamData.forEachOrdered(System.out::println);
    }

//    public void printFinalForEachOrderedParllel(BaseStream<T,S> streamData){
//
//        streamData.forEachOrdered(System.out::println);
//    }

    // Final operation
    public boolean stringReturnNoneMatch(Stream<String> streamData){

       return streamData.noneMatch(s -> s.length() ==3 );
    }

    public boolean stringReturnAllMatch(Stream<String> streamData){

        return streamData.allMatch(s -> s.length() ==3 );
    }

    public boolean stringReturnAnyMatch(Stream<String> streamData){

        return streamData.anyMatch(s -> s.length() ==3 );
    }

    public Optional<String> findFirst(Stream<String> streamData){

        return streamData.<String>filter(s -> s.length() == 3).findFirst();
    }

    public Optional<String> findAny(Stream<String> streamData){

        return streamData.<String>filter(s -> s.length() == 5).findAny();
    }

}
