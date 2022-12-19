import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class PR24Main{

    private static PR24Main instance;
    private int[] vector = new int[100];


    private PR24Main() {
    }
 
    public static PR24Main getInstance() {
        if (instance == null) {
            instance = new PR24Main();
        }
        return instance;    
    }

    public static void main(String args[]) {
        //  
        for (int i = 0; i < getInstance().vector.length; i++) {
            getInstance().vector[i] = 0;
        }
        
        CompletableFuture<Boolean> future0 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future4 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future5 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future6 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future7 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future8 = CompletableFuture.supplyAsync(getSupplier());
        CompletableFuture<Boolean> future9 = CompletableFuture.supplyAsync(getSupplier());

        List<CompletableFuture<Boolean>> futureList = new ArrayList<>();
        futureList.add(future0);
        futureList.add(future1);
        futureList.add(future2);
        futureList.add(future3);
        futureList.add(future4);
        futureList.add(future5);
        futureList.add(future6);
        futureList.add(future7);
        futureList.add(future8);
        futureList.add(future9);
        futureList.forEach(CompletableFuture::join);

        for (int i = 0; i < futureList.size(); i++) {
            try {
                futureList.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        for (int it : getInstance().vector) {
            System.out.print(it);
        }

    }
    static Supplier<Boolean> getSupplier(){
        return new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                Integer ran = ((int) (Math.random() * 5));
                for (int i = 0; i < ran; i++) {
                    Integer pos = ((int) (Math.random() * 99));
                    if(getInstance().vector[pos] == 0){
                        getInstance().vector[pos] = 1;  
                    } else if ( getInstance().vector[pos] == 1 ) { i--; } 
                }
                return true;
            }
        };
    }

}