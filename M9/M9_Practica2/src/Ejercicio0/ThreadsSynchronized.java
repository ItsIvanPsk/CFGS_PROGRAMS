package Ejercicio0;

class ThreadsSynchronized implements Runnable {
    private static int counter = 0;
    private static final int limit = 20000;
    private static final Object lock = new Object();

    @Override
    public void run() {
        while (counter < limit) {
            increaseCounter();
        }
    }

    private void increaseCounter() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " : " + counter);
            this.counter++;
        }
    }
}