import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PR310Client {
    static ArrayList<String> preguntas = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    static int maxQuestion = 0;

    public static void main(String[] args) {

        preguntas.add("Quin temps farà avui?");
        preguntas.add("Qui guanyarà la lliga?");
        preguntas.add("Quina peli t’agrada més?");
        preguntas.add("Què vas sopar ahir?");
        preguntas.add("Quan et comportaràs com una persona adulta?");
        preguntas.add("A quina hora tornaràs?");
        preguntas.add("On vas?");
        preguntas.add("Amb qui vas?");

        String hostName = "127.0.0.1";
        int port = 4321;
        boolean running = true;

        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        // Iniciar canals de comunicació amb el servidor
        try {
            socket = new Socket(hostName, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) { e.printStackTrace(); }

        // Comunicar-se amb el servidor
        try {
            run(oos,ois);
            TimeUnit.MILLISECONDS.sleep(1000);
            run(oos,ois);
            TimeUnit.MILLISECONDS.sleep(1000);
            run(oos,ois);
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Tancar connexió
        try {
            if (ois != null) ois.close();
            if (oos != null) oos.close();
            if (socket != null) socket.close();
            System.out.println("Client tancat");
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Comunicar-se amb el servidor
    static public boolean run (ObjectOutputStream oos, ObjectInputStream ois) {
        boolean running = true;
        String message = "";
        try {

            // Enviar un missatge al servidor
            int questionIndex = (int) (Math.random() * preguntas.size());
            System.out.print("\nEscriu un missatge pel servidor: " + preguntas.get(questionIndex));

            oos.writeObject(preguntas.get(questionIndex));

            // Rebre resposta del servidor
            message = (String) ois.readObject();
            System.out.println("\nEl servidor ha respost: " + message);

            // Si l'usuari vol acabar la connexió
            if (message.contains("tancar")) { running = false; }

        } catch (EOFException e) {
            running = false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            running = false;
        }

        return running;
    }
}
