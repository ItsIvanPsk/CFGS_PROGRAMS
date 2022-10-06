package Ejercicio0;

        import java.io.ByteArrayInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Nerea", 22));
        personas.add(new Persona("Paco",  25));
        personas.add(new Persona("Fuengi", 21));
        personas.add(new Persona("Diosito",  20));
        personas.add(new Persona("xPeke",  25));

        String path = "./src/Ejercicio0/DataOutIn.dat";

        File file = new File(path);

        FileOutputStream fos = null;
        DataOutputStream dos = null;
        for (int i = 0; i < personas.size(); i++) {

        }
        try {

            fos=new FileOutputStream(file);
            dos=new DataOutputStream(fos);
            writeSerializableObject(personas.get(0), dos);
            writeSerializableObject(personas.get(1), dos);
            writeSerializableObject(personas.get(2), dos);
            writeSerializableObject(personas.get(3), dos);
            writeSerializableObject(personas.get(4), dos);
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

        FileInputStream fis = null;
        DataInputStream dis = null;
        try {

            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);

            System.out.println(readSerializableObject(dis).toString());
            System.out.println(readSerializableObject(dis).toString());
            System.out.println(readSerializableObject(dis).toString());
            System.out.println(readSerializableObject(dis).toString());
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