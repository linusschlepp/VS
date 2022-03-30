package aufgabe_b;

public class Parkhaus {

    private final int maxAmCars;
    private int numberCars;


    public Parkhaus(int maxAmCars) {
        this.maxAmCars = maxAmCars;
        this.numberCars = 0;
    }

    public void increment() {
        synchronized (this) {
            while (this.numberCars >= this.maxAmCars) {
                try {
                    System.out.println("Warten an Schranke: " + Thread.currentThread().getName());
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.numberCars++;
            System.out.println("Einfahrt " + Thread.currentThread().getName() + "  " + this.numberCars);
            this.notifyAll();
        }
    }

    public void decrement() {
        synchronized (this) {
            this.numberCars--;
            System.out.println("Ausfahrt " + Thread.currentThread().getName() + "  " + this.numberCars);
            this.notifyAll();
        }
    }
}
