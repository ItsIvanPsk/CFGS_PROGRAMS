
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class PR131llegir {
    public static void main(String[] args) {
        FileInputStream fis = null;
        DataInputStream dis = null;

        String path = "./PR131HashMapData.ser";
        File file = new File(path);

        try {

            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);

            System.out.println(PR131escriu.readSerializableObject(dis).toString());
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
