package core.java.multithread.chapter8.condition;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ETSTicketingSystem {

    static List<String> listOfIssueType = Arrays.asList(Specialty.NETWORK.issueType, Specialty.OS.issueType,Specialty.DEVICE.issueType);

    static int totalNoOfRequests = 0;
    static final int CAPACITY = 5;

    static boolean isAllRequestCompleted = false;
    public static void main(String args[]){

       new ETSTicketingSystem().performETSSimulation();

    }

    void performETSSimulation(){

        Queue<Ticket> ticketQueue = new LinkedList<>();

        Lock lock = new ReentrantLock();

        Condition requestorCond = lock.newCondition();

        Condition technicianCond = lock.newCondition();

        Condition osCondition = lock.newCondition();

        Condition networkCondition = lock.newCondition();

        Condition deviceCondition = lock.newCondition();


        List<Requestor> requestorList = Arrays.asList(new Requestor("Aaron",ticketQueue, lock,requestorCond,technicianCond,osCondition,networkCondition,deviceCondition), new Requestor("David",ticketQueue, lock,requestorCond,technicianCond,osCondition,networkCondition,deviceCondition), new Requestor("Kevin",ticketQueue, lock,requestorCond,technicianCond,osCondition,networkCondition,deviceCondition), new Requestor("James",ticketQueue, lock,requestorCond,technicianCond,osCondition,networkCondition,deviceCondition), new Requestor("Tyler",ticketQueue, lock,requestorCond,technicianCond,osCondition,networkCondition,deviceCondition));

        List<SupportTechnician> technicianList = Arrays.asList(new SupportTechnician("Carlos",lock,ticketQueue,requestorCond,technicianCond, Specialty.NETWORK.getSpecialty(),osCondition,networkCondition,deviceCondition),new SupportTechnician("Huie",lock,ticketQueue,requestorCond,technicianCond, Specialty.OS.getSpecialty(),osCondition,networkCondition,deviceCondition),new SupportTechnician("Drew",lock,ticketQueue,requestorCond,technicianCond,Specialty.DEVICE.getSpecialty(),osCondition,networkCondition,deviceCondition));



        Thread[] requestorThreads = new Thread[requestorList.size()];

        Thread[] technicianThreads = new Thread[technicianList.size()];

        //int totalNoOfRequests = 0;

        for(int i=0; i<requestorThreads.length; i++){

//            IntStream intStreamWithSizeAndRange = new Random().ints(1, 1, 4);

            //int noOfQueries = new Random().nextInt(3);

//            int noOfQueries = intStreamWithSizeAndRange.findAny().getAsInt();

            //totalNoOfRequests += noOfQueries;

            //requestorList.get(i).setNoOfQueries(noOfQueries);

            requestorThreads[i] = new Thread(requestorList.get(i), requestorList.get(i).getName());

            requestorThreads[i].start();



        }

        for(int i=0; i<technicianThreads.length; i++){

            technicianThreads[i] = new Thread(technicianList.get(i), technicianList.get(i).getName());

            technicianThreads[i].start();


        }

        for(int i=0; i<requestorThreads.length; i++){


            try {
                 requestorThreads[i].join();
             }
                catch(InterruptedException e){

                }

        }


         isAllRequestCompleted = isAllRequestCompleted(requestorList);


        for(int i=0; i<technicianThreads.length; i++){


            try {
                technicianThreads[i].join();
            }
            catch(InterruptedException e){

            }

        }


        System.out.println("\n Final queue status: "+ ticketQueue.size() + " "+ ticketQueue);

//        System.out.println("\n Summary of requestors: "+  requestorList);

//        System.out.println("\n Summary of technicians: "+  technicianList);

        //#1 List of tickets grouped by issue sorted by no of incident

         Map<String, List<Ticket>> groupOfTicketsPerIssue = technicianList.stream().map(SupportTechnician::getTicketList).flatMap(List::stream).collect(Collectors.groupingBy(Ticket::getIssueType, Collectors.toList()));

         System.out.println("Group of tickets "+ groupOfTicketsPerIssue);

        //#2 Rank requestor by no of incidents raised
        List<Requestor> requestorsInOrder = requestorList.stream().sorted(Comparator.<Requestor>comparingInt(a -> a.ticketList.size()).reversed()).collect(Collectors.toList());

        System.out.println(requestorsInOrder);

        //#3 Rank technician by no of tickets closed

        List<SupportTechnician> technicianInOrder = technicianList.stream().sorted(Comparator.<SupportTechnician>comparingInt(a -> a.ticketList.size()).reversed()).collect(Collectors.toList());

        System.out.println(technicianInOrder);





    }


    boolean isAllRequestCompleted(List<Requestor> requestorList){


            for(int i=0; i<requestorList.size(); i++){

                if(!requestorList.get(i).isAllRequestCompleted){

                    return false;
                }
            }


        return true;
    }

}



class Requestor implements Runnable{

    public Requestor(String name, Queue ticketQueue, Lock lock, Condition requestorCond, Condition techcianCond, Condition osCondition, Condition networkCondition, Condition deviceCondition ){
        this.name= name;
        this.ticketQueue = ticketQueue;
        this.lock= lock;
        this.requestorCond = requestorCond;
        this.techcianCond= techcianCond;
        this.networkCondition=networkCondition;
        this.osCondition=osCondition;
        this.deviceCondition=deviceCondition;
    }

    int noOfQueries = 0;

    public void setNoOfQueries(int noOfQueries){
        this.noOfQueries=noOfQueries;
    }

    @Override
    public String toString() {
        return "Requestor{" +
                "noOfQueries=" + noOfQueries +
                ", isAllRequestCompleted=" + isAllRequestCompleted +
                ", name='" + name + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }

    public boolean isAllRequestCompleted = false;
    Condition requestorCond ;

    Condition techcianCond ;

    Condition networkCondition ;

    Condition osCondition ;

    Condition deviceCondition ;
    Lock lock ;
    Queue<Ticket> ticketQueue ;

    String name;

    List<Ticket> ticketList = new ArrayList<>();

    public String getName(){

        return this.name;
    }

    String submitTicket(){
        UUID.randomUUID();

        int index = new Random().nextInt(3);

        String issueType = ETSTicketingSystem.listOfIssueType.get(index);

        Ticket ticket = new Ticket(UUID.randomUUID(), issueType, Status.OPEN.status);
        ticket.setRequestorName(this.name);
        this.ticketList.add(ticket);

        ticketQueue.add(ticket);

        System.out.println("Requestor " + this.name +" has raised an issue (Ticket Info)-> " +ticket + " raised by "+ ticket.getRequestorName() );

        return issueType;
    }

    @Override
    public void run(){

        IntStream intStreamWithSizeAndRange = new Random().ints(1, 1, 4);

        //int noOfQueries = new Random().nextInt(3);

        int noOfQueries = intStreamWithSizeAndRange.findAny().getAsInt();

        setNoOfQueries(noOfQueries);


//        lock.lock();
//        try {
//            for (int i = 0; i < noOfQueries; i++) {
//                while(ticketQueue.size()==ETSTicketingSystem.CAPACITY) {
//                    requestorCond.await();
//                }
//                submitTicket();
//                techcianCond.signal();
//
//            }
//        }
//        catch(Exception e){
//            System.out.println("Exception occured while submitting a ticket! "+ e);
//        }
//        finally{
//            lock.unlock();
//        }


            for (int i = 0; i < noOfQueries; i++) {
                lock.lock();
                try {
                    while (ticketQueue.size() == ETSTicketingSystem.CAPACITY) {
                        //System.out.println("before await Queue size: "+ ticketQueue.size());
                        requestorCond.await();
                    }
                    String specialty = submitTicket();

                    if(specialty.equals(Specialty.OS.specialty)){

                        osCondition.signal();
                    }
                    else if(specialty.equals(Specialty.NETWORK.specialty)){

                        networkCondition.signal();
                    }
                    else if(specialty.equals(Specialty.DEVICE.specialty)){

                        deviceCondition.signal();
                    }
                    else {
                        techcianCond.signalAll();
                    }
                    //Thread.sleep(500);
                }
                catch(Exception e){
                    System.out.println("Exception occured while submitting a ticket! "+ e);
                }
                finally{
                    lock.unlock();
                }

            }

        this.isAllRequestCompleted= true;

    }


}



//class SharedQueue{
//
//    Queue<Ticket> ticketQueue = new LinkedList<>();
//
//}

class Ticket{

    String technician;
    String requestorName;

    UUID ticketID;

    String issueType;

    String status;

    public Ticket(UUID ticketID, String issueType, String status) {
        this.ticketID = ticketID;
        this.issueType = issueType;
        this.status = status;
    }

    public void setRequestorName(String requestorName){
        this.requestorName= requestorName;
    }

    public void setTechnician(String technician){
        this.technician=technician;
    }


    public String getTechnician() {
        return this.technician;
    }

    public String getRequestorName() {
        return this.requestorName;
    }



    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticketID) {
        this.ticketID = ticketID;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "technician='" + technician + '\'' +
                ", requestorName='" + requestorName + '\'' +
                ", ticketID=" + ticketID +
                ", issueType='" + issueType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

enum Status{


    OPEN("2000.000","Open"),
    CLOSED("3000.000","Closed");


    String statusId;
    String status;

    Status(String statusId, String status){

        this.status= status;
        this.statusId= statusId;

    }

    String getStatus(){

        return this.status;
    }

    String getStatusId(){

        return this.getStatusId();
    }


}

enum Specialty{


    OS("OS","OS"),

    DEVICE("Device","Device"),
    NETWORK("Network","Network");


    String issueType;
    String specialty;

    Specialty(String issueType, String specialty){

        this.issueType= issueType;
        this.specialty= specialty;

    }

    String getIssueType(){

        return this.issueType;
    }

    String getSpecialty(){

        return this.specialty;
    }


}

class SupportTechnician implements Runnable{

    int noOfIncidentsclosed = 0;

    public void setNoOfIncidentsclosed(int noOfIncidentsclosed){
        this.noOfIncidentsclosed=noOfIncidentsclosed;
    }
    Lock lock ;

    Queue<Ticket> ticketQueue ;
    String name;

    String age;

    String specialty;

    List<Ticket> ticketList = new ArrayList<>();

    public List<Ticket> getTicketList() {
        return this.ticketList;
    }

    Condition requestorCond;

    Condition techcianCond;

    Condition networkCondition ;

    Condition osCondition ;

    Condition deviceCondition ;



    public SupportTechnician(String name,  Lock lock, Queue<Ticket> ticketQueue, Condition requestorCond, Condition techcianCond, String specialty , Condition osCondition, Condition networkCondition, Condition deviceCondition) {
        this.name = name;
        //this.age = age;
        //this.specialty = specialty;
        this.lock=lock;
        this.ticketQueue=ticketQueue;
        this.requestorCond=requestorCond;
        this.techcianCond=techcianCond;
        this.specialty=specialty;
        this.networkCondition=networkCondition;
        this.osCondition=osCondition;
        this.deviceCondition=deviceCondition;
    }

    @Override
    public void run() {

        while (!ETSTicketingSystem.isAllRequestCompleted|| !ticketQueue.isEmpty() ) {
            lock.lock();
            try {
                //lock.wait();

                if (ticketQueue.isEmpty()) {
                    //techcianCond.await(2,TimeUnit.SECONDS);
                    if(this.specialty.equals(Specialty.OS.specialty)){

                        osCondition.await(2,TimeUnit.SECONDS);
                    }
                    else if(this.specialty.equals(Specialty.NETWORK.specialty)){

                        networkCondition.await(2,TimeUnit.SECONDS);
                    }
                    else if(this.specialty.equals(Specialty.DEVICE.specialty)){

                        deviceCondition.await(2,TimeUnit.SECONDS);
                    }
                    else{
                        techcianCond.await(2,TimeUnit.SECONDS);
                    }


                }

                if(!ticketQueue.isEmpty()) {
                    if(resolveissue()) {
                        requestorCond.signal();
                    }
                    //Thread.sleep(1000);
                }

            } catch (Exception e) {
                System.out.println("Exception occured while resolving an issue! " + e);
            } finally {
                lock.unlock();
            }
        }

        setNoOfIncidentsclosed(ticketList.size());
    }



    boolean resolveissue() {

        if (ticketQueue.peek().issueType.equals(this.specialty)) {

            Ticket ticket = ticketQueue.poll();

            //System.out.println("after poll Queue size: "+ ticketQueue.size());

            ticket.setTechnician(this.name);

            ticket.setStatus(Status.CLOSED.status);

            //this.ticketQueue.add(ticket);

            ticketList.add(ticket);

            System.out.println("Technician " + this.name + " has resolved an issue (Ticket Info)-> " + ticket + " raised by " + ticket.getRequestorName());
            return true;
        }
        else{
            System.out.println( Thread.currentThread().getName() +": Not my specialty! Will pass this to someone else with right expertise. " +this.specialty );
            //Thread.yield();
            try {
                if (this.specialty.equals(Specialty.OS.specialty)) {

                    osCondition.await(2,TimeUnit.SECONDS);
                } else if (this.specialty.equals(Specialty.NETWORK.specialty)) {

                    networkCondition.await(2,TimeUnit.SECONDS);
                } else if (this.specialty.equals(Specialty.DEVICE.specialty)) {

                    deviceCondition.await(2,TimeUnit.SECONDS);
                } else {
                    techcianCond.await(2,TimeUnit.SECONDS);
                }
//                if (this.specialty.equals(Specialty.OS.specialty)) {
//
//                    osCondition.await();
//                } else if (this.specialty.equals(Specialty.NETWORK.specialty)) {
//
//                    networkCondition.await();
//                } else if (this.specialty.equals(Specialty.DEVICE.specialty)) {
//
//                    deviceCondition.await();
//                } else {
//                    techcianCond.await();
//                }
            }
            catch(InterruptedException e){

            }

            return false;
        }


    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "SupportTechnician{" +
                "noOfIncidentsclosed=" + noOfIncidentsclosed +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }
}