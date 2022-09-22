package Ejercicio1;
public class Main2 extends Thread {

    public static void main(String[] args) {

        Threads11_Compartida compartida = new Threads11_Compartida();
        Thread T0 = new Thread(new Threads11(), "Thread 0");
        Thread T1 = new Thread(new Threads11(), "Thread 1");
        Thread T2 = new Thread(new Threads11(), "Thread 2");
        Thread T3 = new Thread(new Threads11(), "Thread 3");

        T0.start();
        T1.start();
        T2.start();
        T3.start();

        try{
            T0.join();
            T1.join();
            T2.join();
            T3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(compartida.valor);
    }
}
