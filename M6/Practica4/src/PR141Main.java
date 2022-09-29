import java.io.*;
import java.util.ArrayList;

public class PR141Main {
    public static void main(String[] args) {
        ArrayList<PR140Persona> personas = new ArrayList<>();

        String path = "./PR140Persones.dat";

        File file = new File(path);

        FileInputStream fis = null;
        DataInputStream dis = null;
        try {

            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);


            System.out.println(readSerializableObject(dis));


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

    static byte[] serializableObjectToBytes (Object obj) {
        // Transforma l'objecte a bytes[]
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) { e.printStackTrace(); }
        return null;
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
