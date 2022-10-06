package Ejercicio1;

import java.io.*;
import java.util.*;

public class PR131escriu {
    public static void main(String[] args) {
        HashMap<String, Integer> hash = new HashMap<>();
        hash.put("Ivan",20);
        hash.put("Nerea",23);
        hash.put("Juan",42);
        hash.put("Pakito",1);
        PR131HashMap hashmap = new PR131HashMap(hash);

        String path = "./src/Ejercicio1/PR131HashMapData.ser";

        File file = new File(path);

        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            fos=new FileOutputStream(file);
            dos=new DataOutputStream(fos);
            writeSerializableObject(hashmap, dos);
            dos.flush();
        } catch (IOException e) { e.printStackTrace();
        } finally {
            try {
                if(fos!=null){ fos.close(); }
                if(dos!=null){ dos.close(); }
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

    static void writeSerializableObject (Object obj, DataOutputStream dos) {
        try {
            // Transforma l'objecte a bytes[]
            byte [] data = serializableObjectToBytes(obj);
            System.out.println(">>>" + data.length);
            // Guarda la longitud de l'array
            dos.writeInt(data.length);
            // Guarda els bytes de l'objecte
            dos.write(data);
        } catch (IOException e) { e.printStackTrace(); }
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
