import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.annotation.processing.SupportedOptions;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

// Compilar amb:
// javac -cp "lib/*:." WsServidor.java
// java -cp "lib/*:." WsServidor

// Tutorials: http://tootallnate.github.io/Java-WebSocket/

public class PR314Servidor extends WebSocketServer {
    static int msg_size = 1000;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<PR314Persona> persones = new ArrayList<>();
    static ArrayList<PR314Animal> animals = new ArrayList<>();
    static ArrayList<String> saluts = new ArrayList<>();
    static ArrayList<String> noms = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8888; 
        boolean running = true;

        persones.add(new PR314Persona("Eva", 18));
        persones.add(new PR314Persona("Eugenio", 45));
        persones.add(new PR314Persona("Luis", 22));
        persones.add(new PR314Persona("Anna", 19));

        animals.add(new PR314Animal("Gos", "Teckel", 4));
        animals.add(new PR314Animal("Gat", "Persa", 8));
        animals.add(new PR314Animal("Ocell", "Periquito", 2));
        animals.add(new PR314Animal("Peix", "Tetra", 5));

        // saluts.add("hola");
        saluts.add("hi");
        saluts.add("hallo");
        saluts.add("ciao");
        saluts.add("olá");
        saluts.add("hej");

        //Nombres para generar PR314Persona
        noms.add("Nerea");
        noms.add("Paco");
        noms.add("Tinnoco");
        noms.add("Alvaro");
        noms.add("Alfonso");

        // Deshabilitar SSLv3 per clients Android
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        PR314Servidor socket = new PR314Servidor(port);
        socket.start();
        System.out.println("WsServidor funciona al port: " + socket.getPort());

        while (running) {
            String line = in.readLine();
            socket.broadcast(line);
            if (line.equals("exit")) {
                running = false;
            }
        }    

        System.out.println("Aturant WsServidor");
        socket.stop(1000);
    }

    public void enviaPersona(WebSocket conn, String name,int edad){
        new PR314Persona("Ivan", 10);
        byte[] msg = new byte[msg_size];
        
        // broadcast();
    }



    public PR314Servidor(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public PR314Servidor(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {

        // Saludem personalment al nou client
        conn.send("Benvingut a WsServer"); 

        // Enviem la direcció URI del nou client a tothom 
        broadcast("Nova connexió: " + handshake.getResourceDescriptor());

        // Mostrem per pantalla (servidor) la nova connexió
        String host = conn.getRemoteSocketAddress().getAddress().getHostAddress();
        System.out.println(host + " s'ha connectat");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

        // Informem a tothom que el client s'ha desconnectat
        broadcast(conn + " s'ha desconnectat");

        // Mostrem per pantalla (servidor) la desconnexió
        System.out.println(conn + " s'ha desconnectat");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {

        if (message.equalsIgnoreCase("list")) {
            // Enviar la llsita de connexions al client
            System.out.println("Llista de connexions:");
            String strList = "";
            for (WebSocket ws : this.getConnections()) {
                strList += " " + getConnectionId(ws);
                
            }
            conn.send(strList);

        } else if (message.contains("to(")) {
            // Missatge privat
        
            // Trobar el client amb aquest identificador
            String strDesti = message.substring(message.indexOf("(") + 1, message.indexOf(")"));
            WebSocket desti = null;
            for (WebSocket ws : this.getConnections()) {
                if (strDesti.compareTo(getConnectionId(ws)) == 0) {
                    desti = ws;
                    break;
                }                
            }

            // Enviar el missatge si s'ha trobat el client o retornar un error en cas contrari
            if (desti != null) {
                String idOrigen = getConnectionId(conn);
                String idDesti = getConnectionId(desti);
                desti.send("Missatge privat de " + idOrigen + ": " + message);
                conn.send("S'ha entregat el missatge privat a: " + idDesti);
                System.out.println("Missatge privat entre " + idOrigen + " i " + idDesti + ": " + message);
            } else {
                conn.send("No s'ha trobat el destí " + strDesti);
            }

        }else if (message.contains("saludo")) {
            System.out.println("SALUTS->" + saluts.size());
            int num = (int) (Math.random() * saluts.size());
            System.out.println(num);
            String salut = saluts.get(num);
            System.out.println(salut);
            // Enviem el missatge del client a tothom
            broadcast(getConnectionId(conn) + " ha dit: " + salut);

            // Mostrem per pantalla (servidor) el missatge
            System.out.println("Broadcast de " + getConnectionId(conn) + ": " + salut);
        }else {
            
            broadcast(getConnectionId(conn) + " ha dit: " + message);
            // Mostrem per pantalla (servidor) el missatge
            System.out.println("Broadcast de " + getConnectionId(conn) + ": " + message);
        }
    }
    
    public PR314Persona genRandPersona(){
        return new PR314Persona(noms.get((int) (Math.random() * noms.size())), (int) (Math.random() * 100));
    }

    public void enviaPersona(){
        PR314Persona persona = genRandPersona();
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {

        // Enviem el missatge del client a tothom
        broadcast(message.array());

        // Mostrem per pantalla (servidor) el missatge
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        // S'inicia el servidor
        System.out.println("Escriu 'exit' per aturar el servidor");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

    public String getConnectionId (WebSocket connection) {
        String name = connection.toString();
        return name.replaceAll("org.java_websocket.WebSocketImpl@", "").substring(0, 3);
    }<  
}