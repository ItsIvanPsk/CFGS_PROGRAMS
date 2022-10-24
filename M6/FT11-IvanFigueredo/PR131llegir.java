import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;

public class PR131llegir {
    public static void main(String[] args) {
        FileInputStream fis = null;
        DataInputStream dis = null;

        String path = "./PR131HashMapData.ser";
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

    static Object bytesToSerializableObject (byte[] data) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            return is.readObject();
        } catch (ClassNotFoundException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }
    static Object readSerializableObject (DataInputStream dis) {
        try {
            // Llegeix la longitud de l'array
            int lgth = dis.readInt();
            byte[] data = new byte [lgth];
            // LLegeix l'array que conté l'objecte
            dis.readFully(data, 0, lgth);
            // Transforma l'array de bytes en objecte
            return bytesToSerializableObject(data);

        } catch (UnsupportedEncodingException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace(); }
        return new java.lang.AbstractMethodError();
    }

}
