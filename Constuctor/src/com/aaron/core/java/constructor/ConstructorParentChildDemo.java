package com.aaron.core.java.constructor;

public class ConstructorParentChildDemo {

    String x = "Aaron";

     ConstructorParentChildDemo(){
         super();
        System.out.println("User defined default constructor");
    }

    ConstructorParentChildDemo(String x){
         this();
        System.out.println("User defined parameterized constructor " + x);
    }

}

class ChildClass extends ConstructorParentChildDemo{

    String x = "";

    ChildClass() {
        super("word");
        System.out.println("User defined Child default constructor");
    }

    ChildClass(String x) {
        this();
        System.out.println("User defined Child parameterized constructor " +x);
    }

    ChildClass(String x, int i) {
        this("Indy");
        System.out.println("User defined Child Two parameterized constructor " +x);
    }

    public void ChildClass(){
        System.out.println("Calling childClass Method");
    }

    public static void main(String args[]){
        ChildClass childClass = new ChildClass("Hello", 5);
    }

}

class SubChild extends ChildClass{

    SubChild() {
        System.out.println("User defined Child default constructor");
    }

    public SubChild(String x){
        System.out.println("User defined Child parameterized constructor " +x);
    }

//    public static void main(String args[]){
//
//        SubChild subChild = new SubChild("Hello");
//    }
}