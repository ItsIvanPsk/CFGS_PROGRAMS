import java.io.File;
import java.io.IOException;

public class Threads3 implements Runnable {
    @Override
    public void run() {
        try {
            Process p = Runtime.getRuntime().exec("sort /o ordenat.txt llista.txt", new String[0], new File("./src/"));
            System.out.println("Ordenación acabada");
        } catch (IOException e) {
            System.out.println("Entra exp");
            throw new RuntimeException(e);
        }
    }
}
