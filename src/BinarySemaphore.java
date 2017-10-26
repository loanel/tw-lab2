public class BinarySemaphore {

    private boolean value;

    public BinarySemaphore() {
        this.value = true;
    }

    public synchronized void P() {
        while (!value) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value = false;
    }

    public synchronized void V() {
        value = true;
        notify();
    }

    /// for task 4
    public boolean getValue() {
        return value;
    }

}

class BinarySemaphoreIf {

    private boolean value;

    public BinarySemaphoreIf() {
        this.value = true;
    }

    public synchronized void P() {
        if (!value) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value = false;
    }

    public synchronized void V() {
        value = true;
        notify();
    }

}