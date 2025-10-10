package network.tcp.autocloseable;

import java.util.Arrays;

public class ResourceExample4 {

    public static void main(String args[]){

        try{
            launchApplication();
            int i =0;
        }catch(CallException e){
            //Arrays.stream(e.getSuppressed()).forEach(s-> System.out.println("Suppressed Child Throwable List: "+ s));

            //When child throwable/Exception Object is suppressed, java adds its child to array internally
            //e.addSuppressed(new CloseException("Exception occured with resource: "+"Resource1"));

            System.out.println("Handling call exception: "+e);
            throw new RuntimeException(e);
        }
        catch(CloseException e){
            System.out.println("Handling close exception: "+e);
            throw new RuntimeException(e);
        }
    }
    static void launchApplication() throws CloseException,CallException{


        // this code resolves major issues
        // 1 - Code readability and no need on null check
        // 2 - Not leaving any resources opened.
        // Try catch with resource also closes resource on the right sequence (last in first out as it considers its dependency from new ones on old ones).
        // If even Exception occurs, Try catch with resource will catch them and add it to its parent Exception after supressing it - suppresion code is above
        // 3- It ensures important core business logic exception is present (as parent) and supresses any additional exceptions as child

        try( Resource2 resource1 = new Resource2("Resource1");
             Resource2 resource2 = new Resource2("Resource2");){

            resource1.call();
            resource2.callEx();
            // JVM calls close method as soon as try block is executed
            // we could see that Jvm closes resource in right sequence : resource 2 -> resource 1
        }catch(CallException e){
            System.out.println("Call Exception occured while calling: "+e);
            //e.printStackTrace();
            throw e;
        }

    }
}
