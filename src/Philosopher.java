import java.util.ArrayList;

public class Philosopher implements Runnable {

    private int leftFork;
    private int rightFork;
    private BinarySemaphore[] semaphoreArray;

    public Philosopher(int leftFork, int rightFork, BinarySemaphore[] semaphores) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.semaphoreArray = semaphores;
    }

    @Override
    public void run() {
        while (true) {
            doSomething(System.nanoTime() + " thinking?");
            semaphoreArray[leftFork].P();
            doSomething(System.nanoTime() + ": Picked up left fork" + (leftFork + 1));
            semaphoreArray[rightFork].P();
            doSomething(System.nanoTime() + ": Picked up right fork" + (rightFork + 1) + " - eating");
            doSomething(System.nanoTime() + ": Put down right fork" + (rightFork + 1));
            semaphoreArray[rightFork].V();
            doSomething(System.nanoTime() + ": Put down left fork " + (leftFork + 1) + " - back to thinking");
            semaphoreArray[leftFork].V();
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
