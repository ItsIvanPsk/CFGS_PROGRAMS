public class Threads11 implements Runnable{
    private static int contador = 0;

    @Override
    public void run() {
        while (contador<5000) {
            contador++;
            Threads11_Compartida.valor++;
        }
    }
}