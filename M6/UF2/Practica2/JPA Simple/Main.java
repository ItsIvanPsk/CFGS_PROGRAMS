

/*

Help: https://www.baeldung.com/hibernate-one-to-many

Compile and run from command line:

rm -f ./bin/*.* ; javac @hibernate.argfile -d ./bin/ *.java
java @hibernate.argfile Main

More detailed errors:
java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages @hibernate.argfile Main

For VisualStudio add: 
"vmArgs": "--add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED" 
at .vscode/launch.json

*/

public class Main {

   public static void main(String[] args) {
      
      Manager.createSessionFactory();
      
      Ciutat ciutat1 = Manager.addCiutat("Cornella", "Spain", 10846);
      Ciutat ciutat2 = Manager.addCiutat("Barcelona", "Spain", 24325);
      Ciutat ciutat3 = Manager.addCiutat("Sevilla", "Spain", 12421);

      Ciutada ciutada1 = Manager.addCiutada(1, "Ivan", "Figueredo", 20);
      Ciutada ciutada2 = Manager.addCiutada(1, "Nerea", "Lopez", 23);

      Ciutada ciutada3 = Manager.addCiutada(2, "Saray", "Gallardo", 24);
      Ciutada ciutada4 = Manager.addCiutada(2, "Jess", "Castillo", 22);

      Ciutada ciutada5 = Manager.addCiutada(3, "Jelu", "Crespo", 26);
      Ciutada ciutada6 = Manager.addCiutada(3, "Javi", "Virues", 25);
      
      System.out.println(Manager.collectionToString(Ciutat.class,Manager.listCollection(Ciutat.class, "")));
      System.out.println(Manager.collectionToString(Ciutada.class,Manager.listCollection(Ciutada.class, "")));
      System.out.println("-----------DELETE-----------");
      Manager.delete(Ciutada.class, ciutada2.getId());
      Manager.delete(Ciutada.class, ciutada4.getId());
      Manager.delete(Ciutada.class, ciutada6.getId());

      Manager.delete(Ciutat.class, ciutat2.getCiutatId());
      System.out.println("-----------POST-DELETE-----------");

      System.out.println(Manager.collectionToString(Ciutat.class,Manager.listCollection(Ciutat.class, "")));
      System.out.println(Manager.collectionToString(Ciutada.class,Manager.listCollection(Ciutada.class, "")));
      
      Manager.close();
   }
}