package Ejercicio1;

import java.util.concurrent.locks.ReentrantLock;

public class Main1 {
    public static void main(String[] args) {
        Thread T0 = new Thread(new ThreadsReentrant());
        Thread T1 = new Thread(new ThreadsReentrant());
        Thread T2 = new Thread(new ThreadsReentrant());
        T0.start();
        T1.start();
        T2.start();
    }

}
