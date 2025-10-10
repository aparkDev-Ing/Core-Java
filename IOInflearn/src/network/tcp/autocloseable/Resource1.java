package network.tcp.autocloseable;

public class Resource1 {
    public Resource1(){

    }

    public Resource1(String name){
        this.name=name;
    }
    String name;
    public void call(){
        System.out.println("Invoking call");
    }

    public void callEx()throws CallException{
        System.out.println("Invoking callEx");
        throw new CallException("Exception occured with resource: "+name);
    }


    public void close(){
        System.out.println("Invoking close: "+name);
    }

    public void closeEx()throws CloseException{
        System.out.println("Invoking closeEx: "+name);
        throw new CloseException("Exception occured with resource: "+name);
    }
}
