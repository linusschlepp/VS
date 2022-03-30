package aufgabe_c;

public class Simulation {

    public static void main(String[] args) {

        Parkhaus parkhaus = new Parkhaus(10);

        // Generate producer threads
        Producer producer = new Producer("Producer", parkhaus);
        producer.setDaemon(true);
        producer.start();


        //Generate consumer threads
        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer("Consumer " +i, parkhaus);
            consumer.setDaemon(true);
            consumer.start();
        }


        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
