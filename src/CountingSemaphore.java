import java.util.Date;

public class CountingSemaphore {
    int permits;
    int waitingCount = 0;
    BinarySemaphore intSemaphore = new BinarySemaphore();
    BinarySemaphore waitSemaphore = new BinarySemaphore();

    public CountingSemaphore(int permits) {
        this.permits = permits;
        waitSemaphore.P();
    }

    public void P() {
        intSemaphore.P();
        if (permits == 0) {
            waitingCount++;
            intSemaphore.V();
            waitSemaphore.P();
        } else {
            permits--;
            intSemaphore.V();
        }
    }

    public void V() {
        intSemaphore.P();
        if (waitingCount > 0) {
            waitingCount--;
            intSemaphore.V();
            waitSemaphore.V();
        } else {
            permits++;
            intSemaphore.V();
        }
    }
}

class CountingSemaphoreTest {

    public void starTest() {

//        Semaphore available = new Semaphore(2, true);
        CountingSemaphore available = new CountingSemaphore(2);
        for (int i = 0; i < 5; i++) {
            String name = "Thread: " + (i + 1);
            new Thread(name) {
                public void run() {
                    try {
                        available.P();
                        System.out.println("Started " + Thread.currentThread() + " at " + new Date());
                        Thread.sleep(5000);
                        System.out.println("Exiting " + Thread.currentThread() + " at " + new Date());
                        available.V();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    }
}
