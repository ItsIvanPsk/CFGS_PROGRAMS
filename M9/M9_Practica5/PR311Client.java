import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class PR311Client {
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
        for (int cnt = 0; cnt < llista.length; cnt = cnt + 1) {
            try {
                // Enviar un enter en forma de byte[]
                byte[] bytesEnviament = transformaStringABytes(llista[cnt]);
                DatagramPacket dp = new DatagramPacket(bytesEnviament, bytesEnviament.length, serverIP, serverPort);
                ds.send(dp);

                // Creem un buffer per rebre un long en forma de byte[]
                byte bufferEntrada[] = new byte[Long.BYTES];
                dp = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                ds.receive(dp);
                String numeroRebut = transformaBytesAString(bufferEntrada);

                // Informem per consola i esperem a fer un altre enviament
                System.out.println("Petició=" + llista[cnt] + "\tResposta=" + numeroRebut);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                System.err.println("Se ha producido un error :" + e);
            }
        }
    }

    static public byte[] transformaStringABytes (String valor) {
        byte[] resultat = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeUTF(valor);
            dos.close();
            resultat = baos.toByteArray();
        } catch (IOException e) { e.printStackTrace();  }
        return resultat;
    }

    static public String transformaBytesAString (byte[] dades) {
        String resultat = "";
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(dades);
            DataInputStream dis = new DataInputStream(bais);
            resultat = dis.readUTF();
        } catch (IOException e) { e.printStackTrace(); }
        return resultat;
    }
}