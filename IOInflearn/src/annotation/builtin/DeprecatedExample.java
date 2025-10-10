package annotation.builtin;

public class DeprecatedExample {

    public static void main(String[] args) {

        DeprecatedExample obj = new DeprecatedExample();

        obj.regularMethod();
        obj.deprecatedMethod1();
        obj.deprecatedMethod2();

    }
    public void regularMethod(){
        System.out.println("Hello World!");
    }
    @Deprecated
    public void deprecatedMethod1(){
        System.out.println("Deprecated1");
    }

    @Deprecated(since = "2.4", forRemoval = true)
    public void deprecatedMethod2(){
        System.out.println("Deprecated2 - will be removed");
    }
}
