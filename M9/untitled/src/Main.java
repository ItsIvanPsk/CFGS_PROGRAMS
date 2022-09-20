
public class Main {

    public static void main(String[] args) {
        Main m = new Main();

        Thread T0 = new Thread(new Threads10(0), "Thread 0");
        Thread T1 = new Thread(new Threads10(0), "Thread 0");
        Thread T2 = new Thread(new Threads10(0), "Thread 0");
        Thread T3 = new Thread(new Threads10(0), "Thread 0");
        Thread T4 = new Thread(new Threads10(0), "Thread 0");
        T0.start();
        T1.start();
        T2.start();
        T3.start();
        T4.start();
        try{
            Thread.sleep(2000);
            T0.stop();
            T1.stop();
            T2.stop();
            T3.stop();
            T4.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}