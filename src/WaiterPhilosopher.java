public class WaiterPhilosopher implements Runnable {

    private int leftFork;
    private int rightFork;
    private BinarySemaphore[] semaphoreArray;
    private Waiter waiter;

    public WaiterPhilosopher(int leftFork, int rightFork, BinarySemaphore[] semaphores, Waiter waiter) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.semaphoreArray = semaphores;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        while (true) {
            doSomething(System.nanoTime() + " thinking");
            if (waiter.checkIfForksAvailable(leftFork, rightFork)) {
                semaphoreArray[leftFork].P();
                semaphoreArray[rightFork].P();
                doSomething(System.nanoTime() + " Taken both left " + leftFork + " and right" + rightFork + " forks, eating");
                doSomething(System.nanoTime() + " Finished eating, leaving forks " + leftFork + " and " + rightFork);
                semaphoreArray[rightFork].V();
                semaphoreArray[leftFork].V();
            }
        }
    }

    public void doSomething(String action) {
        System.out.println(Thread.currentThread().getName() + " " + action);
        try {
            Thread.sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
