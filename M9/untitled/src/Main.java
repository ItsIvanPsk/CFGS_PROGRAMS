
public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.ThreadsStart();
    }
    public void ThreadsStart()
    {

        while (true)
        {
            SelfRun objActivo=new SelfRun();
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e){};
            System.out.println("main invoca stopRequest()");
        }
    }
}