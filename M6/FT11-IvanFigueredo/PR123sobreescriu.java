import java.io.FileWriter;

public class PR123sobreescriu {
    public static void main(String[] args) {

        String[] frases = { "Yo sólo puedo mostrarte la puerta", "Tú eres quien la tiene que atravesar" };

        try {
            FileWriter file = new FileWriter("./frasesMatrix/frases.txt",false);
            for (String linea : frases) {
                file.write(linea + "\n");
            }
            file.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}
