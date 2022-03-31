package aufgabe_a;

public class Simulation {

    public static void main(String[] args) throws InterruptedException {

        Parkhaus parkhaus = new Parkhaus(10);

        // Creates 20 Cars and starts them
        for(int i = 1; i <=20; i++) {
            Thread t1 = new Auto("R -FH" + i, parkhaus);
            t1.setDaemon(true);
            t1.start();
        }


        Thread.sleep(30000);
        System.out.println("Ende der Simulation!");

    }
}
