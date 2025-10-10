public class Main {

    class MainB{

        int x =50;

        int returnValue(){
            return this.x;
        }
    }
    public static void main(String[] args) {

        int x = new Main().new MainB().returnValue();

        System.out.println(x);

    }
}