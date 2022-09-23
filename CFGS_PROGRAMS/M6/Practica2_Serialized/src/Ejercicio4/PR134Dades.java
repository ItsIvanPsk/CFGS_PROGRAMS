package Ejercicio4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PR134Dades {
    public static void main(String[] args) {
        String path = "./src/Ejercicio4/employees.dat";
        Scanner sc = new Scanner(System.in);
        File file = new File(path);

        ArrayList<String> idPersones = new ArrayList<String>();
        String line = "";
        String[] lineArr;
        int id = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            for (int cnt = 0; cnt < lines.size(); cnt = cnt + 1) {
                line = lines.get(cnt);
                lineArr = line.split(",");
                idPersones.add(lineArr[0]);
            }

            // Demanem la persona que es vol modificar
            while (idPersones.indexOf(id) == -1) {
                System.out.print("ID> ");
                System.out.println(idPersones);
                id = sc.nextInt();
                sc.nextLine();
            }
            System.out.println(1);
            System.out.println(lines.get(lines.indexOf(id)));


        } catch (IOException e) { e.printStackTrace();
        } finally {
            if (sc != null) { sc.close(); }
        }

        System.out.println("The changes has been saved succesfully!!");

    }
}
