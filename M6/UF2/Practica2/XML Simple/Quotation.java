import java.io.Serializable;

public class Quotation implements Serializable {
    
      private long quotationId;
   
      private String type;  
      
      public Quotation() {}
    
      public Quotation(String type) {
         this.type = type;
      }

      public long getQuotationId() {
         return quotationId;
      }
    
      public void setQuotationId(long id) {
         this.quotationId = id;
      }
    
      public String getType() {
         return type;
      }
    
      public void setType(String type) {
         this.type = type;
      }
    
      @Override
      public String toString () {
         return this.getQuotationId() + ": " + this.getType();
      }
 }