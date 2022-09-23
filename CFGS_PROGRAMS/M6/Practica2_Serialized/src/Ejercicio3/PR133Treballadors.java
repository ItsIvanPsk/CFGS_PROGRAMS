package Ejercicio3;

import Ejercicio0.Persona;

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

public class PR133Treballadors {
    public static void main(String[] args) {
        ArrayList<Treballador> treballadors = new ArrayList<>();
        treballadors.add(new Treballador(0,"Nerea", "Lopez",1, 2000.0f));
        treballadors.add(new Treballador(1,"Paco", "Lopez",4,2000.0f));
        treballadors.add(new Treballador(2,"Fuengi", "Lopez",3,2000.0f));
        treballadors.add(new Treballador(3,"Diosito",  "Lopez",5, 2000.0f));
        treballadors.add(new Treballador(4,"xPeke",  "Lopez",25, 2000.0f));

        String path = "./src/Ejercicio3/employees.csv";
        Scanner sc = new Scanner(System.in);
        File file = new File(path);

        ArrayList<String> nomPersones = new ArrayList<String>();
        String line = "";
        String[] lineArr;
        int indexNom = -1;
        int indexLinia = -1;
        String nom = "";
        Integer dept_id = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            for (int cnt = 0; cnt < lines.size(); cnt = cnt + 1) {
                line = lines.get(cnt);
                lineArr = line.split(",");
                nomPersones.add(lineArr[1]);
            }

            // Demanem la persona que es vol modificar
            while (nomPersones.indexOf(nom) == -1) {
                System.out.print("Quina persona? ");
                System.out.println(nomPersones.toString());
                nom = sc.nextLine();
            }

            // Demanem el nou codi a guardar
            System.out.print("Nuevo DEPT_ID > ");
            dept_id = sc.nextInt();

            // Modifiquem la lína
            indexNom = nomPersones.indexOf(nom);
            line = lines.get(nomPersones.indexOf(nom));
            lineArr = line.split(",");
            lineArr[3] = dept_id.toString();
            line = String.join(",", lineArr); //  + System.getProperty("line.separator");
            lines.set(nomPersones.indexOf(nom), line);

            Path out = Paths.get(path);
            Files.write(out, lines, Charset.defaultCharset());

        } catch (IOException e) { e.printStackTrace();
        } finally {
            if (sc != null) { sc.close(); }
        }

        System.out.println("The changes has been saved succesfully!!");

    }
}
