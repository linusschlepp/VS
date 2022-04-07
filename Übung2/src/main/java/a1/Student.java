package a1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Student extends Thread{

    private KitchenCounter theke;

    public Student(KitchenCounter theke, String name){
        super(name);
        //setDaemon(true);
        this.theke = theke;
    }


    @Override
    public void run() {
        while (true){
            try{
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(16000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            theke.take();
            System.out.println(Thread.currentThread().getName() + " consumed one Leberkassemmel "+theke.getCounter());
        }
    }

}
