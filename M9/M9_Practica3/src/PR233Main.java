import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class PR233Main {
    public static void main(String args[]) {
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(getRunnable("ABC"));
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(getRunnable("HOLA"));
        CompletableFuture<Void> futureC = CompletableFuture.runAsync(getRunnable("ZXY"));

        System.out.println("Esperant resultats");
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        futureList.add(futureA);
        futureList.add(futureB);
        futureList.add(futureC);
        futureList.forEach(CompletableFuture::join);
        System.out.println("Resultats llestos");

        futureList.forEach(CompletableFuture::join);
        System.out.println("Futurs llestos");

    }
    static Runnable getRunnable(String cadena){
        return new Runnable() {
            @Override
            public void run() {
                String result = "";
                try{
                    for (int i = cadena.length() - 1; i >= 0; i--){
                        Thread.sleep(500);
                        char letra = cadena.charAt(i);
                        result = result + letra;
                        System.out.println("Calculant: " + result);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}