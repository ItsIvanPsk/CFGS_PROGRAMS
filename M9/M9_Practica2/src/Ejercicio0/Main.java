package Ejercicio0;

public class Main {
    public static void main(String[] args) {
        Thread T0 = new Thread(new ThreadsSynchronized());
        Thread T1 = new Thread(new ThreadsSynchronized());
        Thread T2 = new Thread(new ThreadsSynchronized());
        T0.start();
        T1.start();
        T2.start();
    }
}
