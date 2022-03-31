package aufgabe_c;

import java.util.Deque;
import java.util.LinkedList;

public class Parkhaus {


    private final Deque<Auto> cars = new LinkedList<>();
    private final int maxAmCars;


    public Parkhaus(int maxAmCars) {
        this.maxAmCars = maxAmCars;
    }

    //adds cars to the parking lot
    public void increment(Auto car) {
        synchronized (this) {
            while (this.cars.size() > this.maxAmCars-1) {
                try {
                    System.out.println("Parking lot is out of capacity "
                            + Thread.currentThread().getName() + " needs to wait ");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cars.addLast(car);
            System.out.println("Car: "+car.getId() +" delivered by " + Thread.currentThread().getName());
            this.notifyAll();
        }
    }
    //removes cars from the parking lot
    public Auto decrement() {
        synchronized (this) {
            while (this.cars.size() < 1) {
                try {
                    System.out.println("No cars available "
                            + Thread.currentThread().getName() + " needs to wait");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

         //   System.out.println("Car: "+ cars.pollFirst().getId() +" was sold to: " +Thread.currentThread().getName());
            this.notifyAll();
            return cars.pollFirst();

        }
    }
}
