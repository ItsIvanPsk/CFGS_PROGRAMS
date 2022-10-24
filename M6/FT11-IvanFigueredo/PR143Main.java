import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PR143Main {

    static Scanner in = new Scanner(System.in);
    static Document doc;
    String path = "./cursos.xml";
    static PR143Main pr = new PR143Main();
    public static void main(String[] args) {
        boolean rd = true;

        doc = llegirXML(pr.getPath());

        while (rd) {

            String menu = "================ MENU ================\n" +
            "1) Llistar ids de cursos\n" +
            "2) Llistar tutor de curs\n" +
            "3) Llistar número d'alumnes\n" +
            "4) Llistar mòduls\n" +
            "5) Llistar alumnes\n" +
            "6) Afegir alumne\n" +
            "7) Esborrar alumne\n" +
            "8) Sortir\n";

            System.out.println(menu);

            int valor = Integer.parseInt(input("Escull una opció"));

            switch (valor) {
                case 1: // Listar todas las IDS de los cursos
                    mostrarCursosPorID();
                    break;
                case 2:
                    tutorPorID(); // Pide una ID y muestra el nombre del tutor
                    break;
                case 3:
                    alumnosPorCursoID(); // Pide una ID de curso y muestra todos los alumnos de ese curso
                    break;
                case 4:
                    modulosPorCursoID(); // Pide una ID de curso y muestra todos los modulos de ese curso
                    break;
                case 5:
                    mostrarAlumnosPorCurso(); // Pide una ID de curso y
                    break;
                case 6:
                    añadirAlumnoXML();
                    break;
                case 7:
                    eliminarAlumoXML();
                    break;
                case 8:
                    rd = false;
                    System.exit(0);
                default:
            }
        }
        in.close();
    }

    static public void guardarXML(String path) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); // Añade la linea de declaración de documento
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Añade la identación al documento XML

            trimWhitespace(doc); // Elimina los saltos de línea inecesarios

            // Abre el documento y añade el codigo XML
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void trimWhitespace(Node node) {
        // Per algun motiu es guarden salts de línia innecessaris,
        // aquesta funció els neteja deixant l'XML bonic

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                child.setTextContent(child.getTextContent().trim());
            }
            trimWhitespace(child);
        }
    }

    // Funcion para leer el documento XML y devolver el objeto
    static public Document llegirXML(String path) {

        Document doc = null;

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(path);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    static public String input(String text) {
        System.out.print(text + ": ");
        return in.next();
    }
    static ArrayList<String> getIdsCursos () {
        ArrayList<String> result = new ArrayList<>();
        NodeList llista = getNodeList(doc, "/cursos/curs");

        for(int cnt = 0; cnt < llista.getLength(); cnt = cnt + 1) {
            Node node = llista.item(cnt);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) node;
                result.add(elm.getAttribute("id"));
            }
        }
        return result;
    }

    static void mostrarCursosPorID () {
        ArrayList<String> ids = getIdsCursos();
        System.out.println("Cursos: " + ids);
    }

    static void tutorPorID () {
        ArrayList<String> ids = getIdsCursos();
        String id = "";
        while (ids.indexOf(id) == -1) {
            id = input("Escriu l'id de curs: \n" + String.join(", ", ids));
        }

        NodeList llista = getNodeList(doc, "/cursos/curs[@id='" + id + "']/tutor");
        Node node = llista.item(0);
        String text = node.getTextContent();

        System.out.println("Tutor de " + id + ": " + text);
    }

    static void alumnosPorCursoID () {
        ArrayList<String> ids = getIdsCursos();
        String id = "";
        while (ids.indexOf(id) == -1) {
            id = input("Escriu l'id de curs: \n" + String.join(", ", ids));
        }

        NodeList llista = getNodeList(doc, "/cursos/curs[@id='" + id + "']/alumnes/alumne");
        System.out.println("Número d'alumnes a " + id + ": " + llista.getLength());
    }

    static void mostrarAlumnosPorCurso () {
        String txt = "";
        ArrayList<String> ids = getIdsCursos();
        String id = "";
        while (ids.indexOf(id) == -1) {
            id = input("Escriu l'id de curs: \n" + String.join(", ", ids));
        }

        NodeList llista = getNodeList(doc, "/cursos/curs[@id='" + id + "']/alumnes/alumne");

        for(int cnt = 0; cnt < llista.getLength(); cnt = cnt + 1) {
            Node node = llista.item(cnt);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) node;
                String nom = elm.getTextContent();
                txt = txt + nom + "\n";
            }
        }

        txt = txt.substring(0, txt.length() - 1); // Treure l'últim salt de línia

        System.out.println(txt);
    }
    static void modulosPorCursoID () {
        String txt = "";
        ArrayList<String> ids = getIdsCursos();
        String id = "";
        while (ids.indexOf(id) == -1) {
            id = input("Escriu l'id de curs " + String.join(", ", ids));
        }

        NodeList llista = getNodeList(doc, "/cursos/curs[@id='" + id + "']/moduls/modul");

        for(int cnt = 0; cnt < llista.getLength(); cnt = cnt + 1) {
            Node node = llista.item(cnt);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) node;
                String strId = elm.getAttribute("id");
                String titol = elm.getElementsByTagName("titol").item(0).getTextContent();
                txt = txt + strId + ": " + titol + "\n";
            }
        }

        txt = txt.substring(0, txt.length() - 1); // Treure l'últim salt de línia

        System.out.println(txt);
    }

    static void añadirAlumnoXML () {
        ArrayList<String> ids = getIdsCursos();
        String id = "";
        while (ids.indexOf(id) == -1) {
            id = input("Escriu l'id de curs: \n" + String.join(", ", ids));
        }
        String nom = input("Nom de l'alumne");

        NodeList llista = getNodeList(doc, "/cursos/curs[@id='" + id + "']/alumnes");

        Node elmAlumnes = llista.item(0);

        Element nouAlumne = doc.createElement("alumne");
        nouAlumne.appendChild(doc.createTextNode(nom));
        elmAlumnes.appendChild(nouAlumne);

        guardarXML(pr.getPath());
    }

    static void eliminarAlumoXML () {
        ArrayList<String> ids = getIdsCursos();
        String id = "";
        while (ids.indexOf(id) == -1) {
            id = input("Escriu l'id de curs: \n" + String.join(", ", ids));
        }
        String nom = input("Nom de l'alumne:");
        
        NodeList llista = getNodeList(doc, "/cursos/curs[@id='" + id + "']/alumnes/alumne[text() = '" + nom + "']");

        Node elmAlumne = llista.item(0);
        Node parent = elmAlumne.getParentNode();
        parent.removeChild(elmAlumne);
    }

    static public NodeList getNodeList (Document doc, String expression) {
        NodeList llista = null;
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            llista = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) { e.printStackTrace();  }
        return llista;
    }

    public String getPath() {
        return this.path;
    }
}
