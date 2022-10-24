import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR122cat {
    public static void main(String[] args) {
        PR122cat pr = new PR122cat();
        File text = new File("./PR12ReadFile.java");
        Scanner sc = new Scanner(System.in);
        System.out.print("Path: ");
        while (!sc.hasNext()) {
            System.out.println("The option only can be a String\n");
            System.out.print("Path: ");
            sc.nextLine();
        }
        String path = sc.next();
        if (path == null)
        {
            System.out.println("The path can be null");
        }
        else
        {
            File file = new File(path);
            if(file.isFile()) {
                pr.showContent(file);
            }
            else if (file.isDirectory())
            {
                System.out.println("El path no correspon a un arxiu, sinó a una carpeta");
            }
            else
            {
                System.out.println("No existe");
            }
        }
    }

    public void showContent(File f)
    {
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
