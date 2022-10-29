import java.net.URI;
import java.net.URISyntaxException;
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
    public static void main(String[] args) {
        int port = 8888;
        String host = "localhost";
        String location = "ws://" + host + ":" + port;
        String text = "";

        saluts.add("bye");
        saluts.add("adéu");
        saluts.add("ciao");
        saluts.add("sayonara");

        PR314Client client = connecta(location);
        
        while (client.running) {
            text = sc.nextLine();

            try {
                client.send(text);
            } catch (WebsocketNotConnectedException e) {
                System.out.println("Connexió perduda, reconnectant ...");
                client = connecta(location);
            }            

            if (text.compareTo("exit") == 0) {
                client.running = false;
            }
        }

        if (client != null) { client.close(); }
    }

    public PR314Client (URI uri, Draft draft) {
        super (uri, draft);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Has rebut un missatge: " + message);
        if (message.compareTo("exit") == 0) {
            System.out.println("El servidor s'ha aturat");
        }
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
}