import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PR230Main {
    public static void main(String args[]) {
        String cadena = "hola";
        PR230Calcula obj = new PR230Calcula();
        Future<String> future = obj.calculate(cadena);

        while (!future.isDone()) {
            System.out.println("Calculant...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        try {
            String result = future.get();
            System.out.println("Nova Cadena: " + result);
            obj.shutdown();
        } catch (InterruptedException e) { e.printStackTrace();
        } catch (ExecutionException e) { e.printStackTrace(); }
    }
}
