import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class PR125cp {
    public static void main(String[] args) {
        PR125cp pr = new PR125cp();

        Scanner sc = new Scanner(System.in);
        System.out.println("Starting path:");
        while (!sc.hasNext()) {
            System.out.println("The option only can be a String\n");
            System.out.println("Starting path:");
            sc.nextLine();
        }
        String startingPath = sc.next();
        System.out.println("Ending path:");
        while (!sc.hasNext()) {
            System.out.println("The option only can be a String\n");
            System.out.println("Ending path:");
            sc.nextLine();
        }
        String endingPath = sc.next();
        pr.copyFiles(startingPath,endingPath);
    }

    private void copyFiles(String startingPath, String endingPath){
        try{
            Files.copy(Path.of(startingPath), Path.of(endingPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
