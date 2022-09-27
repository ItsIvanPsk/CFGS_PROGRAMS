package Ejercicio1;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadsReentrant implements Runnable {
    static ReentrantLock mutex = new ReentrantLock();
    static int compartida = 0;

    public void perform() {
        mutex.lock();
        try {
            compartida++;
        } finally {
            mutex.unlock();
            System.out.println(compartida);
        }
    }

    @Override
    public void run() {
        this.perform();
    }
}
