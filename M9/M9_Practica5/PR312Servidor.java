import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
public class PR312Servidor
{
    public static void main(String args[])  {

        try {
            System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
        } catch (UnknownHostException uhe) {
            System.err.println("No s'ha pogut obrir la direcció local :" + uhe); 
        }

        int port = 4456;
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
        } catch(SocketException e) {
            System.err.println("No s'ha pogut obrir el socket al port: " + port);
            System.exit(-1);
        }

        DatagramPacket dp = null;
        boolean running = true;

        while (running) {
            try {
                // Creem un buffer per rebre un Integer en forma de byte[] i el llegim
                byte bufferEntrada[] = new byte[1000];
                dp = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(dp);
                String missatgeRebut = transformaBytesAString(bufferEntrada) + palindromo(transformaBytesAString(bufferEntrada));
                System.out.println("Missatge: " + missatgeRebut);
                byte[] bytesEnviament = transformaStringABytes(missatgeRebut);
                
                // Enviem el paquet amb les dades al client que correspon
                int portClient = dp.getPort();
                InetAddress ipClient = dp.getAddress();
                dp = new DatagramPacket(bytesEnviament, bytesEnviament.length, ipClient, portClient);
                socket.send(dp);
                System.out.println( "Client=" + ipClient + ":" + portClient + "\tEntrada=" + missatgeRebut + "\tEnviament=" + missatgeRebut );

            } catch (IOException e) { 
                e.printStackTrace(); 
                running = false;
            }
        }
        if (socket != null) socket.close();
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
    static public String palindromo(String cadena){
        String result = cadena;
        for(int i = 0; i < result.length(); i++){
            char letra = cadena.charAt(i);
            result = result + letra;            
        }
        return result;
    }
}
