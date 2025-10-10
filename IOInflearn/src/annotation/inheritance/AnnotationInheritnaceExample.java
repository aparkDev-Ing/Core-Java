package annotation.inheritance;

import annotation.inheritance.utility.AnnotationUtility;

public class AnnotationInheritnaceExample {
    public static void main(String[] args) {
        AnnotationUtility.print(Parent.class);
        AnnotationUtility.print(Child.class);
        AnnotationUtility.print(TestInterface.class);
        AnnotationUtility.print(TestInterfaceImpl.class);
    }
}


@InheritedTest
@NonInheritedTest
class Parent {

}


class Child extends Parent {

}

class TestInterfaceImpl implements TestInterface{

}