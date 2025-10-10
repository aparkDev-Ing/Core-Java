package efficientfilestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class InefficientFileStreamExample {

    static void launchApplication() throws InterruptedException{
        
        Thread mainThread = Thread.currentThread();
        
        String book = "book";
        Thread customerThread = new Thread(()->{
           placeOrder(book);
           try {
//               mainThread.join();
               Thread.sleep(6000);
               mainThread.interrupt();
               System.out.println("Customer is tired of waiting! Leaving restaurant");
           }
           catch(InterruptedException e){
               System.out.println("Error occured while waiting on main thread "+ e);
           }
       },"Customer");

       //customerThread.start();

        //prepareOrder(book);

        readOrder();
        //readAll();
        
    }
    
    static void prepareOrder(String book) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" | Author is preparing for customer book: "+book);
        try {
            long startTime = System.currentTimeMillis();
            FileOutputStream fos = new FileOutputStream(BufferConstants.FILE_NAME);
            for(int i =0; i<BufferConstants.FILE_SIZE; i++){
                fos.write(1);
            }
            long endTime = System.currentTimeMillis();

            System.out.println("Time it took to complete: "+ (endTime - startTime)+" ms");
            System.out.println("File Name: "+ BufferConstants.FILE_NAME);
            System.out.println("File Size: "+ (BufferConstants.FILE_SIZE/1024/1024) +" MB");

        }
        catch(Exception e){
            System.out.println("Error occured while waiting on main thread "+ e);
            System.out.println("Auther could not complete writing a book on time");
            return;
        }

        System.out.println(Thread.currentThread().getName()+" | Author is completed for customer book: "+book);
    }

    static void readOrder(){

        try {
            long startTime = System.currentTimeMillis();
            FileInputStream fis = new FileInputStream(BufferConstants.FILE_NAME);
            int content;
            int fileSize = 0;
            while((content = fis.read()) != -1){
                //System.out.println(content);
               fileSize++;
            }
            long endTime = System.currentTimeMillis();

            System.out.println("Time it took to complete: "+ (endTime - startTime)+" ms");
            System.out.println("File Name: "+ BufferConstants.FILE_NAME);
            System.out.println("File Size: "+ (fileSize/1024/1024) + " MB");

        }
        catch(Exception e){
            System.out.println("Error occured while waiting on main thread "+ e);
            System.out.println("Auther could not complete writing a book on time");
            return;
        }

    }

    static void readAll(){

        try {
            long startTime = System.currentTimeMillis();
            FileInputStream fis = new FileInputStream(BufferConstants.FILE_NAME);



            byte[] contents = fis.readAllBytes();
            long endTime = System.currentTimeMillis();

            System.out.println("Time it took to complete: "+ (endTime - startTime)+" ms");
            System.out.println("File Name: "+ BufferConstants.FILE_NAME);
            //System.out.println(contents[0]);
            //System.out.println("File Size: "+ (fileSize/1024/1024) + " MB");

        }
        catch(Exception e){
            System.out.println("Error occured while waiting on main thread "+ e);
            System.out.println("Auther could not complete writing a book on time");
            return;
        }

    }
    
    static void placeOrder(String book){
        System.out.println(Thread.currentThread().getName() +" has placed a order: "+ book);
        
    }

    public static void main(String args[])throws InterruptedException{
        launchApplication();
    }

}
