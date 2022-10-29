import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class PR311Client {
    static int msg_size = 1000;
    public static void main(String args[]) {

        InetAddress serverIP = null;
        try {
            serverIP = InetAddress.getByName("127.0.0.1");
        } catch(UnknownHostException uhe) {
            System.err.println("Servidor no trobat : " + uhe);
            System.exit(-1);
        }

        int serverPort = 4456;
        DatagramSocket ds = null;
        try
        {
            ds = new DatagramSocket();
        } catch(SocketException se) {
            System.err.println("No s'ha pogut obrir el socket al port : " + serverPort);
            System.exit(-1);
        }

        // Crides que farem al servidor
        String[] llista = new String[4];
        llista[0] = "Jirafa";
        llista[1] = "Elefant";
        llista[2] = "Porc";
        llista[3] = "Lleo";

        // Per cada una de les crides
        for (int cnt = 0; cnt < llista.length; cnt++) {
            try {
                // Enviar un enter en forma de byte[]
                byte[] bytesEnviament = stringToBytes(msg_size, llista[cnt]);
                DatagramPacket dp = new DatagramPacket(bytesEnviament, bytesEnviament.length, serverIP, serverPort);
                ds.send(dp);

                // Creem un buffer per rebre un long en forma de byte[]
                byte bufferEntrada[] = new byte[msg_size];
                dp = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                ds.receive(dp);
                String numeroRebut = bytesToString(bufferEntrada);

                // Informem per consola i esperem a fer un altre enviament
                System.out.println("Petició=" + llista[cnt] + "\tResposta=" + numeroRebut);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                System.err.println("Se ha producido un error :" + e);
            }
        }
    }

    static byte[] stringToBytes (int mida, String text) {
        byte[] resultat = new byte[mida];
        try {
            // Crea un array amb la mida i la cadena de text
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeUTF(text);
            oos.flush();
            byte [] bytesResultat = bos.toByteArray();
           
            // Guarda els bytes (de mida i cadena) a l'array resultat
            for (int cnt = 0; cnt < bytesResultat.length; cnt = cnt + 1) {
                resultat[cnt] = bytesResultat[cnt];
            }
        } catch (IOException e) { e.printStackTrace(); }
        return resultat;
    }
    static String bytesToString (byte[] bytes) {
        String resultat = "";
        try {
  
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(in);
            resultat = is.readUTF();
  
        } catch (UnsupportedEncodingException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace(); }
        return resultat;
    }
 

}