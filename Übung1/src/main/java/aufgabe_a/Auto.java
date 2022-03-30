package aufgabe_a;

import java.util.Random;

public class Auto extends Thread {

    private final Parkhaus parkhaus;


    public Auto(String id, Parkhaus parkhaus) {
       // super(id);
        setName(id);
        this.parkhaus = parkhaus;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            parkhaus.increment();
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            parkhaus.decrement();
        }
    }





}
