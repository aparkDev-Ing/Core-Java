package core.java.multithread.chapter12.forkjoinpool;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {

    public int start;
    public int end;
    public List<Order> orderList ;

    public CustomRecursiveTask(int start, int end, List<Order> orderList){
        this.start=start;
        this.end=end;
        this.orderList=orderList;
    }
    @Override
    protected Integer compute() {


        if(start==end){
            return orderList.get(start).price;
        }

        int mid =  start+ (end-start)/2;

        CustomRecursiveTask left = new CustomRecursiveTask(start,mid,orderList);
        CustomRecursiveTask right = new CustomRecursiveTask(mid+1,end,orderList);

        left.fork();
        Integer rightResult = right.compute();
        Integer leftResult = left.join();

        return leftResult+rightResult;
    }
}
