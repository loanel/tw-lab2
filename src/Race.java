class RaceBuffer{
    int value;
    BinarySemaphoreIf sem = new BinarySemaphoreIf();

    public RaceBuffer(int initial){
        this.value = initial;
    }

    public void increment(){
        sem.P();
        value++;
        sem.V();
    }

    public int getValue(){
        return this.value;
    }
}

class Race extends Thread {

    RaceBuffer rb;

    public Race(RaceBuffer rb){
        this.rb = rb;
    }

    public void run() {
        for (int i = 0; i < 1000000; ++i) {
//            System.out.println(Thread.currentThread() + "increments value");
            rb.increment();
        }
    }
}

