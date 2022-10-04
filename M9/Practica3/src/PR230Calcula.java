import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PR230Calcula {

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    public Future<String> calculate(String cadena) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            String result = "";
            for (int i = 0; i < cadena.length(); i++)
            {
                Thread.sleep(1500);
                char letra = cadena.charAt(i);
                result = result + String.valueOf((char) (letra + 1));
                System.out.println("Nova cadena: " + result);
            }
            return result;
        });
    }
    public void shutdown () { executor.shutdown(); }

}
