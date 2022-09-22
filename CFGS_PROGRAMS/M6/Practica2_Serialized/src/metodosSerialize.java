import java.io.*;

public class metodosSerialize {
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
