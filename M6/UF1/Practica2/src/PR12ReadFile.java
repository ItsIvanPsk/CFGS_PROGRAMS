import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR12ReadFile {
    public static void main(String[] args) {
        int rowNum = 1;
        File text = new File("./PR12ReadFile.java");
        Scanner sc;
        try {
            sc = new Scanner(text);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println("line " + rowNum + " :" + line);
                rowNum++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
