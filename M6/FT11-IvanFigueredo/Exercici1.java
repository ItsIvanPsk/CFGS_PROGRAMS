import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
 * Mostra les següents dades de l'arxiu de 'filePath'
 * - Si existeix
 * - Si és ocult
 * - Quan es va modificar
 * - Si es pot modificar
 * - Llista d'arxius d'aquesta carpeta
 */

// Compilar i executar amb:
// javac Exercici0.java
// java Exercici0

public class Exercici1 {
    public static void main(String args[], String string) {
        String basePath = System.getProperty("user.dir") + "/";
        String filePath = basePath + "Exercici1.java";
        File file = new File(filePath);

        boolean existeix = false;
        if (file.exists()) { existeix = true;}
        System.out.println("L'arxiu existeix: " + existeix);

        boolean ocult = false;
        if (file.isHidden()) { ocult = true;}
        System.out.println("L'arxiu és ocult: " + ocult);

        Date modificat = new Date(file.lastModified());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyy hh:mm:ss");
        String strModificat = dateFormat.format(modificat);
        System.out.println("Última modificació: " + strModificat);

        boolean esPotModificar = true;
        if (file.canWrite()) { esPotModificar = true;}
        System.out.println("L'arxiu es pot modificar: " + esPotModificar);

        File carpeta = new File(basePath);
        String[] names = carpeta.list();
        /*
         * try{
            File dir = new File(basePath);
            String[] aux = Files.list((Path) basePath);
            ArrayList<String> llistaArxius = 
    
            System.out.println("La llista d'arxius d'aquesta carpeta és: " + llistaArxius);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        

    }
}