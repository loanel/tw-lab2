

public class Main {

    public static void main(String[] args) {
        ///1

//        RaceBuffer rb = new RaceBuffer(0);
//        System.out.println(rb.getValue());
//        Race a = new Race(rb);
//        Race b = new Race(rb);
//        a.start();
//        b.start();
//        try{
//            a.join();
//            b.join();
//        } catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println(rb.getValue());

        /// 2
        // Petla while jest wymagana poniewaz watek ktory otrzyma notyfikacje nie ma gwarancji ze warunek jest ustawiony dobrze.
        // Gdy watek sie budzi, nie moze zakladac ze stan w ktorym czekal byl nadal poprawny. Stan ten mogl zmienic sie pomiedzy wykonaniem notify() a zanim watek obudzil sie.
        // Kolejnym powodem jest to ze  systemy nie dzialaja idealnie i moga wystapic tzw "spurious wakeup"
        // Spurious wakeup to losowe wybudzenie sie watku bez bycia zasygnalizowanym. Jest rzadkim ale mozliwym zdarzeniem ktore wystepuje w implementacji watkow Java
        // https://docs.oracle.com/javase/6/docs/api/java/lang/Object.html#wait%28%29

        /// 3

//        CountingSemaphoreTest test = new CountingSemaphoreTest();
//        test.starTest();



        /// 4 + 5


        // variable declaration

        Philosopher[] philosophers = new Philosopher[5];
        int[] forks = new int[5];
        BinarySemaphore[] binarySemaphores = new BinarySemaphore[5];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = i;
            binarySemaphores[i] = new BinarySemaphore();
        }

        /// Rozwiazanie naiwne, nie dziala poniewaz moze nastapic cykl i przykladowo wszyscy filozofowie zdecyduja sie na wybranie lewego widelca jako pierwszego, co spowoduje deadlock
//        NaiveSolution solution1 = new NaiveSolution();
//        solution1.philosophersProblemSolution(forks, philosophers, binarySemaphores);

        /// Rozwiazanie 1 - 4 filozofowie zawsze wybieraja lewy widelec jako pierwszy, ostatni zawsze wybiera prawy widelec
        /// W ten sposob łamiemy cykliczne oczekiwanie i pozbywamy się deadlocku
        /// (resource hierarchy solution)
//        RightForkSolution rfs = new RightForkSolution();
//        rfs.philosophersProblemSolution(forks, philosophers, binarySemaphores);

        /// Rozwiazanie 2 - tworzymy kelnera ktory pozwala filozofowi podniesc tylko 0 lub 2 widelce na raz
        /// w ten sposob nigdy nie dojdzie do cyklu powodujacego deadlock
        /// rozwiazanie to moze powodowac zmniejszenie stopnia wsplobieznego jedzenia filozofow
        WaiterSolution wsol = new WaiterSolution();
        WaiterPhilosopher[] waiterPhilosophers = new WaiterPhilosopher[5];
        wsol.philosophersProblemSolution(forks, waiterPhilosophers, binarySemaphores);


    }
}
