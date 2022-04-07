package a1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {

    private final int capacity;
    private int counter = 0;
    Lock monitor = new ReentrantLock();
    Condition consume = monitor.newCondition();
    Condition produce = monitor.newCondition();

    public KitchenCounter(int capacity) {
        this.capacity = capacity;
    }

    public void put(){
        monitor.lock();
        try {
            while(this.counter >= this.capacity){
                try{
                    System.out.println("We have enough Leberkassemmeln " +
                            Thread.currentThread().getName() + " can rest");
                    produce.await();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            this.counter++;
            consume.signal();
        }finally {
            monitor.unlock();
        }
    }



    public void take(){
        monitor.lock();
        try {
            while(this.counter == 1){
                try{
                    System.out.println("We are out of Leberkassemmeln " +
                            Thread.currentThread().getName() + " needs to wait");
                    consume.await();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            this.counter--;
            produce.signal();
        }finally {
            monitor.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}
