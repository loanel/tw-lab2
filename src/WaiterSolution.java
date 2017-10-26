public class WaiterSolution {

    public void philosophersProblemSolution(int[] forks, WaiterPhilosopher[] waiterPhilosophers, BinarySemaphore[] binarySemaphores) {
        Waiter waiter = new Waiter(binarySemaphores);
        for (int i = 0; i < waiterPhilosophers.length; i++) {
            int leftFork = forks[i];
            int rightFork = forks[(i + 1) % forks.length];

            waiterPhilosophers[i] = new WaiterPhilosopher(leftFork, rightFork, binarySemaphores, waiter);

            Thread t = new Thread(waiterPhilosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }

}
