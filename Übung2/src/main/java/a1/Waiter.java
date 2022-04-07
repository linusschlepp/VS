package a1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Waiter extends Thread{

    private KitchenCounter theke;


    public Waiter(KitchenCounter theke, String name){
        super(name);
       // setDaemon(true);
        this.theke = theke;
    }

    @Override
    public void run() {
        while (true){
            try{
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            theke.put();
            System.out.println(Thread.currentThread().getName() + " produced one Leberkassemmel "+theke.getCounter());
        }
    }
}
