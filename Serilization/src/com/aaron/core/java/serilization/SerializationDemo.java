package com.aaron.core.java.serilization;



import java.io.*;


class CheeseBurger extends Burger{

    public String name = "CheeseBurger";

    public final double price = 7.50;

    public CheeseBurger(String pattie, String bread){

        this.bread = bread;

        this.pattie = pattie;
    }

    @Override
    public String toString(){
        return this.name +" "+this.pattie +" "+ this.bread + " "+this.price +" "+this.brand;
    }

}

class KimchiBurger extends Burger{

    public String name = "KimchiBurger";
//    public String brand = "'s";

    public final double price = 8.00;

    public KimchiBurger(String bread, String pattie){
        this.bread = bread;
        this.pattie= pattie;


    }

    @Override
    public String toString(){
        return this.name +" "+this.pattie +" "+ this.bread + " "+this.price +" "+this.brand;
    }

}

class Burger <T extends Burger> implements Serializable {

    public String name ="Burger";
    public final double price = 5.00;
    public String bread;
    public String pattie;
    public String brand = "Aaron's";

    public Burger(){

        System.out.println("Burger Constructor called!");
    }

    public Burger( String bread) {

        this.bread = bread;
    }

    public String toString(){

        return this.name +" "+this.pattie +" "+ this.bread + " "+this.price +" "+this.brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public String getPattie() {
        return pattie;
    }

    public void setPattie(String pattie) {
        this.pattie = pattie;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

public class SerializationDemo <T extends Burger> {

    public static Burger castToBurger(Object object){

        Burger burger = null;

        if(object instanceof Burger){
            burger = (Burger) object;
        }

        else if(object instanceof CheeseBurger){
             burger = (CheeseBurger) object;
        }

        else if(object instanceof KimchiBurger){
            burger = (KimchiBurger) object;
        }

        return burger;
    }

//    public static T castToBurger2(Object object){
//
//        Burger burger = null;
//
//        if(object instanceof Burger){
//            burger = (Burger) object;
//        }
//
//        else if(object instanceof CheeseBurger){
//            burger = (CheeseBurger) object;
//        }
//
//        else if(object instanceof KimchiBurger){
//            burger = (KimchiBurger) object;
//        }
//
//        return burger;
//    }

    public static void main(String args[]) throws Exception{

//        Burger burger = new Burger( "Plain Bread");
//
//        CheeseBurger cheeseBurger = new CheeseBurger("Beef","Sesame Bread");

        KimchiBurger kimchiBurger = new KimchiBurger("Plain Bread","Chicken");

        kimchiBurger.setBrand("'s");

        FileOutputStream fos = new FileOutputStream("burgers.ser");

//        System.out.println("Kimchi Burger "+kimchiBurger);

        ObjectOutputStream oos = new ObjectOutputStream(fos);


//        oos.writeObject(burger);
//
//        oos.writeObject(cheeseBurger);

        oos.writeObject(kimchiBurger);

        FileInputStream fis = new FileInputStream("burgers.ser");

        ObjectInputStream ois = new ObjectInputStream(fis);

        Object object1 = ois.readObject();

//        Object object2 = ois.readObject();
//
//        Object object3 = ois.readObject();

//        System.out.println(castToBurger(ois.readObject()) );
//
//        System.out.println(castToBurger(ois.readObject()) );
//
//        System.out.println(castToBurger(ois.readObject()) );

        System.out.println(castToBurger(object1) );

//        System.out.println(castToBurger(object2) );
//
//        System.out.println(castToBurger(object3) );


    }

}
