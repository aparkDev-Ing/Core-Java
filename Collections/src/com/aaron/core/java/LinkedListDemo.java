package com.aaron.core.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListDemo {

    public static void main(String args[]){

        LinkedList<String> stringLinkedList = new LinkedList<>();

        stringLinkedList.add("Aaron");

        stringLinkedList.add("James");

        stringLinkedList.add("Devin");

        stringLinkedList.add("Madelynn");

        ListIterator<String> stringIterator = stringLinkedList.listIterator(stringLinkedList.size()-1);

        while(stringIterator.hasPrevious()){

            String s = stringIterator.previous();

            System.out.println("Index : "  +stringLinkedList.indexOf(s)  +" / Name of person: " + s);
        }




    }
}
