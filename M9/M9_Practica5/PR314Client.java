import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

// Compilar amb:
// javac -cp "lib/*:." WsClient.java
// java -cp "lib/*:." WsClient

public class PR314Client extends WebSocketClient {

    private boolean running = true;
    private static Scanner sc = new Scanner(System.in);
    static ArrayList<String> saluts = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String> races = new ArrayList<>();

    public static void main(String[] args) {
        int port = 8888;
        String host = "localhost";
        String location = "ws://" + host + ":" + port;
        String text = "";

        saluts.add("bye");
        saluts.add("adéu");
        saluts.add("ciao");
        saluts.add("sayonara");

        names.add("Pipo");
        names.add("Kibbi");
        names.add("Kerry");
        names.add("Golfy");

        races.add("Pastor Aleman");
        races.add("Shiba");
        races.add("Shiba Inu");
        races.add("Golden Terrier");



        PR314Client client = connecta(location);
        
        while (client.running) {
            text = sc.nextLine();
            if (text.compareTo("enviaAnimal") == 0) {
                try {
                    PR314Animal animal = genRandomAnimal();
                    client.send(objToBytes(animal));
                } catch (WebsocketNotConnectedException e) {
                    System.out.println("Connexió perduda, reconnectant ...");
                    client = connecta(location);
                }
            }
            if (text.compareTo("exit") == 0) {
                client.running = false;
            } else {
                try {
                    client.send(text);
                } catch (WebsocketNotConnectedException e) {
                    System.out.println("Connexió perduda, reconnectant ...");
                    client = connecta(location);
                }
            }

        }

        if (client != null) { client.close(); }
    }

    private static PR314Animal genRandomAnimal() {
        return new PR314Animal(names.get((int) (Math.random() * names.size())),
                                races.get((int) (Math.random() * races.size())), 
                                (int) (Math.random() * 15));
    }

    public PR314Client (URI uri, Draft draft) {
        super (uri, draft);
    }

    // Recibir mensaje del servidor
    @Override
    public void onMessage(String message) {
        System.out.println("Has rebut un missatge: " + message);
        if (message.compareTo("exit") == 0) {
            System.out.println("El servidor s'ha aturat");
        }
    }

    // Recibir objeto del servidor
    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        System.out.println("Has rebut un missatge: " + bytesToObject(byteBuffer).toString());
    }


    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("T'has connectat a: " + getURI());
        System.out.println("list per veure la llista de ids");
        System.out.println("to(id)missatge per enviar missatges privats");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("T'has desconnectat de: " + getURI());
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error amb la connexió del socket");
    }

    static public PR314Client connecta (String location) {
        PR314Client client = null;

        try {
            client = new PR314Client(new URI(location), (Draft) new Draft_6455());
            client.connect();
        } catch (URISyntaxException e) { 
            e.printStackTrace(); 
            System.out.println("Error: " + location + " no és una direcció URI de WebSocket vàlida");
        }

        return client;
    }

    public static byte[] objToBytes (Object obj) {
        byte[] result = null;
        try {
            // Transforma l'objecte a bytes[]
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            result = bos.toByteArray();
        } catch (IOException e) { e.printStackTrace(); }
        return result;
    }
  
    public static Object bytesToObject (ByteBuffer arr) {
        Object result = null;
        try {
            // Transforma el ByteButter en byte[]
            byte[] bytesArray = new byte[arr.remaining()];
            arr.get(bytesArray, 0, bytesArray.length);
  
            // Transforma l'array de bytes en objecte
            ByteArrayInputStream in = new ByteArrayInputStream(bytesArray);
            ObjectInputStream is = new ObjectInputStream(in);
            return is.readObject();
  
        } catch (ClassNotFoundException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace(); }
        return result;
    }
 
}