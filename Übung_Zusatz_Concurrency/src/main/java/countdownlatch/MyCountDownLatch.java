package countdownlatch;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyCountDownLatch {


    Timer timer = new Timer();
    private int count;

    public MyCountDownLatch(int count) throws IllegalAccessException {
        if (count < 0)
            throw new IllegalAccessException();
        this.count = count;

    }

    // CurrentThread has to wait until the latch has counted down to zero, unless the thread is interrupted
    public void await() throws InterruptedException {
        synchronized (this) {
            while (this.count > 0)
                this.wait();
        }
    }

    // releases all waiting threads if count reaches zero
    public void countDown() {

        synchronized (this) {
            if (this.count > 0) {
                this.count--;
                if (this.count == 0) {
                    this.notifyAll();
                }
            }
        }

    }


    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {

        long milliBreak = unit.toMillis(timeout);
        // When to stop
        long timeOutMillis = System.currentTimeMillis() + milliBreak;


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                synchronized (MyCountDownLatch.this) { // Referring to object of outer class
                    MyCountDownLatch.this.notifyAll();
                }
            }
        };

        timer.schedule(timerTask, milliBreak);


        // Disable as long as count > 0 and the time has not run out
        synchronized (this) {
            while (System.currentTimeMillis() < timeOutMillis && this.count > 0) {
                this.wait();
            }
        }


        // return true immediately, if count ins zero
        synchronized (this) {
            return this.count == 0;

        }


    }


}
