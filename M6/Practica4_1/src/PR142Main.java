import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PR142Main {
    public static void main(String[] args) {
        String path = "./PR141Persones.xml";
        File file = new File(path);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList llista = doc.getElementsByTagName("persona");
            System.out.println("Número de persones: " + llista.getLength());

            for(int cnt = 0; cnt < llista.getLength(); cnt = cnt + 1) {
                Node node = llista.item(cnt);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element) node;

                    String txt = "";
                    int i = 0;
                    Integer _int = 0;
                    txt = txt + "Nom: "      + elm.getElementsByTagName("nom").item(0).getTextContent();
                    txt = txt + ", Cognom: "   + elm.getElementsByTagName("cognom").item(0).getTextContent();
                    txt = txt + ", Edat: "       + elm.getAttribute("edat");
                    txt = txt + ", Ciutat: "       + elm.getElementsByTagName("ciutat").item(0).getTextContent();

                    System.out.println(txt);
                }
            }


        } catch(Exception e) { e.printStackTrace(); }
    }
}
