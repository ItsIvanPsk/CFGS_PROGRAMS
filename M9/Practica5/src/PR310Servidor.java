import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PR310Servidor {
    static HashMap<String, String> pyr = new HashMap<>();
    static private ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String args[]) {

        int port = 4321;
        ServerSocket serverSocket = null;
        boolean running = true;

        // Inicia el sevidor de sockets
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor funcionant al port: " + port);
        } catch (IOException e) { e.printStackTrace(); }

        // Espera i assigna noves connexions
        while (running) {
            try {
                PR310Servidor.clientConnection(serverSocket.accept());
                System.out.println("Nou client connectat");
            } catch (IOException e) { e.printStackTrace(); }
        }

        // Tancar els fils i el servidor de sockets (aquí no hi arriba mai)
        try {
            executor.shutdown();
            serverSocket.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Gestiona les connexions de sockets de cada client
    static public Future<Integer> clientConnection(Socket socket) {
        return (Future<Integer>) executor.submit(() -> {
            // Obrir els canals de comunicació amb el client
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            boolean running = true;

            // Comunicar-se amb el client
            while (running) { running = run(ois, oos); }

            // Tancar socket amb el client
            ois.close();
            oos.close();
            socket.close();
            System.out.println("Client tancat");
            return 0;
        });
    }

    // Comunicar-se amb el client
    static public boolean run (ObjectInputStream ois, ObjectOutputStream oos) {
        boolean running = true;
        try {
            // Esperar a rebre un missatge del client
            System.out.println("Esperant petició del client");
            String message = (String) ois.readObject();
            System.out.println("El servidor ha rebut: " + message);

            // Donar resposta al missatge del client
            String pyr_response = "";
            switch (message){
                case "Quin temps farà avui?" : pyr_response = "Espero que no llueva"; break;
                case "Qui guanyarà la lliga?": pyr_response = "Acabará en empate"; break;
                case "Quina peli t’agrada més?": pyr_response = "Iron Man 2"; break;
                case "Què vas sopar ahir?": pyr_response = "Tip Sirloin Roast"; break;
                case "Quan et comportaràs com una persona adulta?": pyr_response = "Ara"; break;
                case "A quina hora tornaràs?": pyr_response = "A les 10"; break;
                case "On vas?": pyr_response = "A casa"; break;
                case "Amb qui vas?": pyr_response = "Amb la meva parella"; break;
                default: pyr_response = "Me agobias"; break;
            }
            oos.writeObject(pyr_response);

            // Si el client vol acabar la connexió
            if (message.contains("tancar")) { running = false; }

        } catch (SocketException | EOFException e) {
            System.out.println("El client ha desaparegut");
            running = false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            running = false;
        }
        return running;
    }
    /*
    public static void setPyr(){
        this.pyr.put("Quin temps farà avui?","Espero que no llueva");
        this.pyr.put("Qui guanyarà la lliga?","Acabará en empate");
        this.pyr.put("Quina peli t’agrada més?","Iron Man 2");
        this.pyr.put("Què vas sopar ahir?","Tip Sirloin Roast");
        this.pyr.put("Quan et comportaràs com una persona adulta?","Ara");
        this.pyr.put("A quina hora tornaràs?","A les 10");
        this.pyr.put("On vas?","A casa");
        this.pyr.put("Amb qui vas?","Amb la meva parella");
    }
     */

}
