package aufgabe_c;

import java.util.Random;

public class Consumer extends Thread {

    private final Parkhaus parkhaus;

    public Consumer(String name, Parkhaus parkhaus){
        super(name);
        this.parkhaus = parkhaus;
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(new Random().nextInt(15000));
            }catch (InterruptedException e){
                e.printStackTrace();
                return;
            }
            // Car is sold to the consumer and therefore removed from the parking lot
            System.out.println("Car: "+this.parkhaus.decrement().getId() +" was sold to: " +Thread.currentThread().getName());
        }

    }
}
