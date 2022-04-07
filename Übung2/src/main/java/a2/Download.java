package a2;


import javax.swing.JProgressBar;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


// aktive Klasse
public class Download extends Thread
{

    private final JProgressBar balken;


    CountDownLatch start;
    CountDownLatch stop;


    public Download(CountDownLatch start, CountDownLatch stop, JProgressBar balken) {
        this.balken = balken;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public void run() {

           try {
               start.await();
               for (int i = 0; i <= balken.getMaximum(); i++){
                   try {
                       TimeUnit.MILLISECONDS.sleep(new Random().nextInt(120));
                       balken.setValue(i);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
           }
               stop.countDown();
           } catch (Exception e) {
               e.printStackTrace();
           }

       }

    }


    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */


