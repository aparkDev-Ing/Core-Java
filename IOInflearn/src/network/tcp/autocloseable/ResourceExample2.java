package network.tcp.autocloseable;

public class ResourceExample2 {

    public static void main(String args[]){

        try{
            launchApplication();
        }catch(CallException e){
            System.out.println("Handling call exception: "+e);
            throw new RuntimeException(e);
        }
        catch(CloseException e){
            //Third issue - it replaces an important initial business logic exception information because of exception that occured while closing a resource
            // which is not major information we want. We want an exception that caused this business logic to fail to troubleshoot (Banking transfer program for example)
            System.out.println("Handling close exception: "+e);
            throw new RuntimeException(e);
        }
    }
    static void launchApplication() throws CloseException,CallException{

        //First Issue = we initialize variables with null because issue can happen
        // while assigning a object to variable later - to catch in try block. However, this can lead to null pointer later if
        // issue ever happens while assigning a variable to resource1 and it enters finally block : resource2 = null
        // this had code readability issue thing

        Resource1 resource1 =null;
        Resource1 resource2 =null;

        try {
            resource1= new Resource1("Resource1");
            resource2=new Resource1("Resource2");
            resource1.call();
            resource2.callEx();
        }
        catch(CallException e){
            System.out.println("Call Exception happened while invoking a call: "+e);
            throw e;
        }
        finally {

            // First Issue = have to handle null pointer - not a best code readability
            if(resource2 != null){
                resource2.closeEx();
                // Second issue - issue occurs while closing a resource 2 : this leads to critical issue where
                // we cannot reach next line to close resource1 which will cause an issue later (leaving resource opened)
            }
            if(resource1!=null) {
                resource1.close();
            }
        }



    }
}
