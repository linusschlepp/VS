package aufgabe_c;

import java.util.Random;

public class Producer extends Thread {

    private final Parkhaus parkhaus;

    public Producer(String name, Parkhaus parkhaus){
        super(name);
        this.parkhaus = parkhaus;
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(new Random().nextInt(new Random().nextInt(3000)));
            }catch (InterruptedException e){
                return;
            }
            // Car gets produced and added to the parking lot
            this.parkhaus.increment(new Auto("R - " + new Random().nextInt(100)));
        }
    }
}
