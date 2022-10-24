import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PR210Honor {
    
    static ResultSet rs = null;
    static ResultSet rs2 = null;
    static Scanner in = new Scanner(System.in);
    Connection conn = null;
    
    public static void main(String[] args) throws SQLException {
        String basePath = System.getProperty("user.dir") + "/";
        String filePath = basePath + "database.db";

        // Si no hi ha l'arxiu creat, el crea i li posa dades
        File fDatabase = new File(filePath);
        if (!fDatabase.exists()) { initDatabase(filePath); }
        Connection conn = UtilsSQLite.connect(filePath);

        boolean rd = true;

        while (rd) {

            String menu = "================ MENU ================\n" +
            "1) Mostrar taula\n" +
            "2) Mostrar personatges\n" +
            "3) Mostrar millor atacant\n" +
            "4) Mostrar millor defensant\n" +
            "5) Sortir\n";

            System.out.println(menu);

            int valor = Integer.parseInt(input("Escull una opció"));

            switch (valor) {
                case 1: 
                    getAllTables(conn);
                    getTable(conn, input("Nombre de la tabla"));
                    break;
                case 2:
                    System.out.println(getFaccion(conn).toString());
                    getAllPersonatges(conn, Integer.parseInt(input("Faccion ID:")));
                    break;
                case 3:
                    getHigherDPS(conn);
                    break;
                case 4:
                    getHigherDEF(conn);
                    break;
                case 5:
                    in.close();
                    System.exit(0);
            }
        }
        in.close();
    }

    private static void getAllPersonatges(Connection conn, int factionID) {
        String ln = "";
        try {
            System.out.println("---------- Personajes ---------- ");
            rs2 = UtilsSQLite.querySelect(conn,"SELECT * FROM Faccio where id = " + factionID + ";");
            String name = rs2.getString("name");
            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM personatges where idFaccio =" + factionID + " ;");
            while (rs.next()) {
                ln = name + " - " 
                + rs.getInt("id") + "    "
                + rs.getString("name") + "    "
                + rs.getInt("atac") + "    "
                + rs.getInt("defensa") + "\n";
                System.out.println(ln);
                ln = "";
            }
            
        } catch (SQLException e) {
            System.out.println("Error!");
        }
    }

    public static void getHigherDPS(Connection conn){
        ArrayList<Integer> DPS = new ArrayList<>();
        Integer id_max_dps = 0;
        try {
            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM personatges;");
            System.out.println("Contingut de la taula:");
            while (rs.next()) {
                DPS.add(rs.getInt(3));
            }
            id_max_dps = 1;

            for(int i = 0; i < DPS.size() - 1; i++){
                if(DPS.get(i) < DPS.get(i+1)){ id_max_dps = i + 1;}
            }

            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM personatges where id = " + id_max_dps + ";");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
            }


        } catch (SQLException e) {
            System.out.println("Error!");
        }
    }

    public static void getHigherDEF(Connection conn){
        ArrayList<Integer> DEF = new ArrayList<>();
        Integer id_max_def = 0;
        try {
            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM personatges;");
            System.out.println("Contingut de la taula:");
            while (rs.next()) {
                DEF.add(rs.getInt(4));
            }
            id_max_def = 1;
            for(int i = 0; i < DEF.size() - 1; i++){
                if(DEF.get(i) < DEF.get(i+1)){ id_max_def = i + 1;}
            }

            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM personatges where id = " + id_max_def + ";");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
            }


        } catch (SQLException e) {
            System.out.println("Error!");
        }
    }

    public static ArrayList<String> getAllTables(Connection conn){
        // Llistar les taules
        ArrayList<String> tables = UtilsSQLite.listTables(conn);
        System.out.println(tables);
        return tables;
    }

    public static ArrayList<Integer> getFaccion(Connection conn){
        ArrayList<Integer> arr = new ArrayList<>();
        try {
            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM Faccio;");
            while (rs.next()) {
                arr.add(rs.getInt(1)); 
            }
            return arr;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arr;
    }

    public static void getTable(Connection conn, String tableName){
        String table = "";
        String aux = "";

        try {
            rs = UtilsSQLite.querySelect(conn, "SELECT count(*) FROM " + tableName + ";");
            int rows = 0;
            while (rs.next()) {
                rows = rs.getInt(1);
                System.out.println(rows);
            }
            System.out.println("Table:");
            
            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM " + tableName + ";");
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for(int item = 1; item < cols + 1; item++){
                    aux = aux + rs.getString(item) + " ";
                    System.out.println("Item: " + item);
                }
                table = table + aux + "\n";
                aux = "";
            }
            System.out.println(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFaccio(String tableName){
        String faccio = "";
        // SELECT a la base de dades
        try {
            rs = UtilsSQLite.querySelect(conn, "SELECT * FROM faccio;");
            System.out.println("Faccions:");
            while (rs.next()) {
                faccio = faccio + (rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("resum")).toString();  
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return faccio;
    }

    static void initDatabase (String filePath) {
        // Connectar (crea la BBDD si no existeix)
        Connection conn = UtilsSQLite.connect(filePath);

        // Esborrar la taula (per si existeix)
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS faccio;");
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS personatges;");


        // Crear una nova taula
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS faccio ("
                                    + "	id integer PRIMARY KEY AUTOINCREMENT,"
                                    + "	name varchar2(15) NOT NULL,"
                                    + "	resum varchar2(500) NOT NULL);");

        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS personatges ("
                                    + "	id integer PRIMARY KEY AUTOINCREMENT,"
                                    + "	name varchar2(15) NOT NULL,"
                                    + " atac real NOT NULL,"
                                    + " defensa real NOT NULL,"
                                    + " idFaccio integer);");

        // Afegir elements a una taulas
        UtilsSQLite.queryUpdate(conn, "INSERT INTO faccio (name, resum) VALUES (\"Caballeros\", \"Los Caballeros son exprisioneros y reclutas a la fuerza, ascendidos a soldados de élite. Son guerreros bien armados, con un estilo basado en la defensa y el uso de enormes manguales con los que minan la resistencia de su oponente, hasta que se rinde ante su portentosa fuerza.\");");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO faccio (name, resum) VALUES (\"Vikingos\", \"Los Vikingos son caóticos y brutales que usan hachas dobles. Su salvaje pasión por el combate aterra a todos, amigos o enemigos... Un Vikingo lucha sin descanso, es capaz de vencer al enemigo antes de que pueda montar una defensa decente.\");");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO faccio (name, resum) VALUES (\"Samurais\", \"Samurais. Nacidos para lucha, siempre están la avanzadilla de las fuerzas Samurais. Son la expresión perfecta de sus ideales: bravura, integridad y pasión.\");");

        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Prior Oscuro\", 2, 55, 1);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Warmonger\", 4, 135, 1);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Justiciero\", 5, 13, 1);");

        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Shinobi\", 5, 15, 2);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Kensei\", 0, 15, 2);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Kyoshin\", 5, 234, 2);");

        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Invasor\", 52, 515, 3);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Chaman\", 533, 154, 3);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO personatges (name, atac, defensa, idFaccio) VALUES (\"Berseker\", 55, 165, 3);");

        // Desconnectar
        UtilsSQLite.disconnect(conn);
    }

    static public String input(String text) {
        System.out.print(text + ": ");
        return in.nextLine();
    }
}
