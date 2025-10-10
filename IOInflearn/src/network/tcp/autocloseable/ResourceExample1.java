package network.tcp.autocloseable;

public class ResourceExample1 {

    public static void main(String args[]){

        try{
            launchApplication();
        }catch(CallException e){
            System.out.println("Handling call exception: "+e);
            throw new RuntimeException(e);
        }
        catch(CloseException e){
            System.out.println("Handling close exception: "+e);
            throw new RuntimeException(e);
        }
    }
    static void launchApplication() throws CloseException,CallException{

        Resource1 resource1 =new Resource1("Resource1");
        Resource1 resource2 =new Resource1("Resource2");

        resource1.call();
        resource2.callEx();

        resource2.close();
        resource1.closeEx();
    }
}
