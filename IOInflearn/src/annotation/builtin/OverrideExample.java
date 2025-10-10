package annotation.builtin;

public class OverrideExample {


    static class A{
        public void call(){
            System.out.println("Called by A");
        }
    }
    static class B extends A{
        //@Override complier indicates that it's not eligible for override
        @Override
        public void call(){
            System.out.println("Called by B");
        }

        public void calllll(){
            System.out.println("Called by B");
        }
    }

    public static void main(String[] args) {


        A a = new B();

        a.call();
    }

}

class TestParent{

    static class C{

    }

    public static void main(String[] args) {
        TestParent testParent = new TestParent();

        //C c = testParent.new C();

    }
}