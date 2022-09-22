package Ejercicio2;

public class Main3 {
    public static void main(String[] args) {
        System.out.println("Ordenant...");
        Thread T0 = new Thread(new Threads3());
        T0.setDaemon(false);
        T0.start();
        System.out.println("Programa acabat");
    }
}