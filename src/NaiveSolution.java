public class NaiveSolution {
    public void philosophersProblemSolution(int[] forks, Philosopher[] philosophers, BinarySemaphore[] binarySemaphores) {
        for (int i = 0; i < philosophers.length; i++) {
            int leftFork = forks[i];
            int rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopher(leftFork, rightFork, binarySemaphores);

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}
