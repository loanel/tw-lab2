public class Waiter {
    BinarySemaphore[] binarySemaphores;

    public Waiter(BinarySemaphore[] semaphores) {
        binarySemaphores = semaphores;
    }

    public boolean checkIfForksAvailable(int leftFork, int rightFork) {
        return binarySemaphores[leftFork].getValue() && binarySemaphores[rightFork].getValue();
    }
}
