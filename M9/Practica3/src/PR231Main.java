import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PR231Main {
    public static void main(String args[]) {
        PR231Calcula obj = new PR231Calcula();
        Future<String> f0 = obj.calculate("hola");
        Future<String> f1 = obj.calculate("adios");
        Future<String> f2 = obj.calculate("muy");
        String txt = "";

        while (!f0.isDone() || !f1.isDone() || !f2.isDone()) {
            // Esperar a que estiguin llestos
            txt = String.format(
                    "Estats: f0 %s, f1 %s, f2 %s",
                    f0.isDone() ? "llest" : "calculant",
                    f1.isDone() ? "llest" : "calculant",
                    f2.isDone() ? "llest" : "calculant"
            );
            System.out.println(txt);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        try {
            txt = String.format(
                    "Resultat: f0=%s, f1=%s, f2=%s",
                    f0.get(), f1.get(), f2.get()
            );
            System.out.println(txt);
            obj.shutdown();
        } catch (InterruptedException e) { e.printStackTrace();
        } catch (ExecutionException e) { e.printStackTrace(); }
    }
}
