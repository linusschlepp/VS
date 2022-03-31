package aufgabe_b;

public class Simulation {

    public static void main(String[] args) throws InterruptedException {

        Parkhaus parkhaus = new Parkhaus(10);

        for(int i = 1; i <=20; i++) {
            Thread t1 = new Auto("R -FH" + i, parkhaus);
            t1.setDaemon(true);
            t1.start();
        }


        Thread.sleep(30000);
        System.out.println("Ende der Simulation!");

    }
}
