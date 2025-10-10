package reflection.restaurant;

import java.lang.reflect.Method;
import java.util.Scanner;

public class KioskExample {



    public static void main(String args[]){
        launchApplication();
    }

    static void launchApplication(){
        new Kiosk().prompt();
    }
}


class Kiosk{

    public void selectMenu(String menu){
        System.out.println("Customer has selected menu: "+menu);

    }

    public void seeMenu(){
        System.out.println("1. Burger 2. Pizza 3. Taco");
    }

    public void prompt() {
        Scanner scan = new Scanner(System.in);

        Class<? extends Kiosk> kioskClass = Kiosk.class;

        try {
            while (true) {
                System.out.println("Please enter what you would like to do: 1.see menu | 2. place order | 3. Exit" );
                int selection = scan.nextInt();
                scan.nextLine();
                switch (selection) {
                    case 1:
                        Method method1 = kioskClass.getDeclaredMethod("seeMenu");
                        method1.invoke(this);
                        break;

                    case 2:
                        System.out.println("Please enter a order: ");
                        String food = scan.nextLine();
                        Method method2 = kioskClass.getDeclaredMethod("selectMenu", String.class);
                        method2.invoke(this,food);
                        break;
                    case 3:
                        System.out.println("Exit out of store");
                        break;
                    default:
                        System.out.println("Invalid selection.");
                        break;
                }

                if(selection == 3){
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception occurred: "+e);
            e.printStackTrace();
        }



    }

}

