import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PR124linies {

    public static void main(String[] args) {
        String path = "./src/numeros.txt";
        try {
            File f = new File(path);
            FileWriter myWriter = new FileWriter(path);
            f.createNewFile();

            for (int i = 0; i < 10; i++)
            {
                Integer num = (int)(Math.random() * 10);
                System.out.println(num);
                myWriter.write(num.toString() + "\n");
            }
            System.out.println("fns");
            myWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
