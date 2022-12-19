import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class PR232Main {
    public static void main(String args[]) {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(getSupplier("HOLA"));
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(getSupplier("PAMELA"));
        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(getSupplier("DEUXTALANDE"));


        // Posar els 'CompletableFuture' en una llista per esperar-los tots alhora
        System.out.println("Esperant resultats");
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(futureA);
        futureList.add(futureB);
        futureList.add(futureC);
        futureList.forEach(CompletableFuture::join);
        System.out.println("Resultats llestos");

        // Mostrar resultats
        try {
            String resultA = futureA.get();
            String resultB = futureB.get();
            String resultC = futureC.get();
            System.out.println("Resultat A = " + resultA + ", Resultat B = " + resultB + ", Resultat C = " + resultC);
        } catch (InterruptedException e) { e.printStackTrace();
        } catch (ExecutionException e) { e.printStackTrace(); }

    }
    static Supplier<String> getSupplier(String cadena){
        return new Supplier<String>() {
            @Override
            public String get() {
                String result = cadena;
                try{
                    for (int i = 0; i < cadena.length(); i++){
                        Thread.sleep(500);
                        char letra = cadena.charAt(i);
                        result = result + letra;
                        System.out.println("Calculant: " + result);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return result;
            }
        };
    }
}