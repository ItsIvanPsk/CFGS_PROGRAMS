import java.net.*;
import java.io.*;
public class PR312Servidor
{
    static int msg_size = 1000;
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
                byte bufferEntrada[] = new byte[msg_size];
                dp = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(dp);
                String missatgeRebut = bytesToString(bufferEntrada);
                System.out.println("Missatge: " + missatgeRebut);
                missatgeRebut = missatgeRebut + palindromo(missatgeRebut);
                byte[] bytesEnviament = stringToBytes(msg_size, missatgeRebut);
                                
                // Enviem el paquet amb les dades al client que correspon
                int portClient = dp.getPort();
                InetAddress ipClient = dp.getAddress();
                dp = new DatagramPacket(bytesEnviament, bytesEnviament.length, ipClient, portClient);
                socket.send(dp);

            } catch (IOException e) { 
                e.printStackTrace(); 
                running = false;
            }
        }
        if (socket != null) socket.close();
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

    
    static String palindromo(String str){
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--){
            char letra = str.charAt(i);
            result = result + letra;
            System.out.println("Calculant: " + result);
        }
        return result;
    }
    
}
