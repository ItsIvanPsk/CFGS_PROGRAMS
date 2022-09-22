package Ejercicio0;
public class Threads10 implements Runnable{
    private int numThread;

    public Threads10(int numThread)
    {
        this.numThread = numThread;
    }

    public static void main(String[] args) {

    }
    @Override
    public void run() {
        try {
            Thread.sleep(10);
            System.out.println("Hola mon: " + numThread);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
