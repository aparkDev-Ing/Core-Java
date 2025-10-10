package network.tcp.autocloseable;

public class Resource2 implements AutoCloseable {
    public Resource2(){

    }

    public Resource2(String name){
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


    @Override
    public void close() throws CloseException{
        System.out.println("Invoking close: "+name);
        throw new CloseException("Exception occured with resource: "+name);
    }

//    public void closeEx()throws CloseException{
//        System.out.println("Invoking closeEx: "+name);
//        throw new CloseException("Exception occured with resource: "+name);
//    }
}
