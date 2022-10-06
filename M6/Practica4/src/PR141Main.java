import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PR141Main {
    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<PR140Persona> personas = new ArrayList<>();

        String path = "./PR140Persones.dat";
        String pathOut = "./<PR141Persones.xml";

        File file = new File(path);

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {

            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            //System.out.println(ois.readObject().toString());
            // System.out.println(readSerializableObject(ois));
            personas = new ArrayList<>(Arrays.asList((PR140Persona[]) ois.readObject()));

            // Crear estructura d'arbre XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
        
            Element elmRoot = doc.createElement("persones");
            doc.appendChild(elmRoot);

            for (int cnt = 0; cnt < personas.size(); cnt = cnt + 1) {
                PR140Persona obj = personas.get(cnt);

                Element elmPersona = doc.createElement("persona");

                Element elmNom = doc.createElement("nom");
                elmNom.appendChild(doc.createTextNode(obj.getName()));
                elmPersona.appendChild(elmNom);

                Element elmCognom = doc.createElement("cognom");
                elmCognom.appendChild(doc.createTextNode(obj.getSurname()));
                elmPersona.appendChild(elmCognom);

                Attr attrEdat = doc.createAttribute("edat");
                attrEdat.setValue(Integer.toString(obj.getAge()));
                elmPersona.setAttributeNode(attrEdat);

                Element elmCiutat = doc.createElement("ciutat");
                elmCiutat.appendChild(doc.createTextNode(obj.getCity()));
                elmPersona.appendChild(elmCiutat);

                elmRoot.appendChild(elmPersona);
            }

            // Guardar l'arxiu XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = <ransformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathOut));
            transformer.transform(source, result);

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fis!=null){ fis.close(); }
                if(ois!=null){ ois.close(); }
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

    static Object readSerializableObject (ObjectInputStream dis) {
        try {
            // Llegeix la longitud de l'array
            int lgth = dis.readInt();
            byte[] data = new byte [lgth];
            // LLegeix l'array que conté l'objecte
            dis.readFully(data, 0, lgth);
            // Transforma l'array de bytes en objecte
            return bytesToSerializableObject(data);

        } catch (IOException e) { e.printStackTrace(); }
        return new java.lang.AbstractMethodError();
    }
}
