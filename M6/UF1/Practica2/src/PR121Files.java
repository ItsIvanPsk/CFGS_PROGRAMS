import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class PR121Files {

    public static void main(String[] args) {
        File theDir = new File("./PR121Files.java");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        try {
            File myObj = new File("./myFiles/file1.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            File myObj2 = new File("./myFiles/file2.txt");
            if (myObj2.createNewFile()) {
                System.out.println("File created: " + myObj2.getName());
            } else {
                System.out.println("File already exists.");
            }

            System.out.println("El 1r llistat és:");
            String[] path;
            File files = new File("./myFiles/");
            path = files.list();

            for (String paths: path)
            {
                System.out.println(" * " + paths);
            }

            myObj2.renameTo(new File("./myFiles/renamedFile"));

            myObj.delete();

            System.out.println("El 2n llistat és:");
            String[] path2;
            File files2 = new File("./myFiles/");
            path2 = files.list();

            for (String paths2: path2)
            {
                System.out.println(" * " + paths2);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
