import java.io.*;
import java.util.ArrayList;

public class PR140Main {
    public static void main(String[] args) {

        ArrayList<PR140Persona> personas = new ArrayList<>();

        personas.add(new PR140Persona("Maria", "Lopez", 36, "Barcelona"));
        personas.add(new PR140Persona("Gustavo", "Catadasús", 15, "Londres"));
        personas.add(new PR140Persona("Irene", "Rocheford", 45, "Tokio"));
        personas.add(new PR140Persona("Armengol", "Pastor", 72, "Abidjan"));

        String path = "./PR140Persones.dat";

        File file = new File(path);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            fos=new FileOutputStream(file);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(personas.toArray(new PR140Persona[0]));

            oos.flush();
            System.out.println(personas.toString());
            
        } catch (IOException e) { e.printStackTrace();
        } finally {
            try {
                if(fos!=null){ fos.close(); }
                if(oos!=null){ oos.close(); }
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
}