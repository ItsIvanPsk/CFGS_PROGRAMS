package Ejercicio1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static Ejercicio1.PR131escriu.readSerializableObject;

public class PR131llegir {
    public static void main(String[] args) {
        FileInputStream fis = null;
        DataInputStream dis = null;

        String path = "./src/Ejercicio1/PR131HashMapData.ser";
        File file = new File(path);

        try {

            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);

            System.out.println(readSerializableObject(dis).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null){ fis.close(); }
                if(dis!=null){ dis.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
