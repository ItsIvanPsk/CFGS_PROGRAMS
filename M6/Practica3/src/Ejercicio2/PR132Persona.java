import java.io.*;
import java.util.ArrayList;

public class PR132Persona implements Serializable {

    private String name;
    private String sur_name;
    private int age;

    public PR132Persona(String name, String sur_name, int age) {
        this.name = name;
        this.sur_name = sur_name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PR132Persona{" +
                "name='" + name + '\'' +
                ", sur_name='" + sur_name + '\'' +
                ", age=" + age +
                '}';
    }


    public static void main(String[] args){
        PR132Persona persona0 = new PR132Persona("Maria", "Lopez", 36);
        PR132Persona persona1 = new PR132Persona("Gustavo", "Ponts", 63);
        PR132Persona persona2 = new PR132Persona("Irene", "Sales", 54);


        String path = "./PR132people.dat";

        File file = new File(path);

        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {

            fos = new FileOutputStream(file);
            dos = new DataOutputStream(fos);
            writeSerializableObject(persona0, dos);
            writeSerializableObject(persona1, dos);
            writeSerializableObject(persona2, dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (dos != null) {
                    dos.close();
                }
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
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

    class Persona implements Serializable {
        private String name;
        private int age;
        public Persona(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName(){
            return this.name;
        }
        public int getAge(){
            return this.age;
        }

        @Override
        public String toString() {
            return "Persona{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}