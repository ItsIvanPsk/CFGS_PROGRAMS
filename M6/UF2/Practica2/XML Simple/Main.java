

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

      Quotation ref1 = Manager.addQuotation("Quotation 1");
      Quotation ref2 = Manager.addQuotation("Quotation 2");
      Quotation ref3 = Manager.addQuotation("Quotation 3");
      Manager.addQuotation("Quotation 4");

      Manager.updateQuotation(ref1.getQuotationId(), "Quotation 1x");
      Manager.updateQuotation(ref2.getQuotationId(), "Quotation 2x");


      Manager.delete(Quotation.class, ref3.getQuotationId());

      System.out.println(Manager.collectionToString(Quotation.class, Manager.listCollection(Quotation.class, "")));

      Manager.close();
   }
}