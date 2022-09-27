package Ejercicio0;

public class SelfRun implements Runnable{
    private Thread internalThread;
    private boolean noStopRequired;
    public SelfRun(){
        noStopRequired=true;
        internalThread=new Thread(this);
        internalThread.start();
    }
    public void run(){
        while(noStopRequired){
            System.out.println(internalThread.getName());
            try{ Thread.sleep(500);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();}
        }
    }
    public void stopRequest(){
        noStopRequired=false;
        internalThread.interrupt();
    }
}