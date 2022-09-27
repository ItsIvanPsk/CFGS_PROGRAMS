package EjemplosCodigo;

public class Pistola {
    private boolean enposicio = true;
    public void disparar(int cartucho) {
        while (enposicio == false) {
            try { wait(); } catch (InterruptedException e) { }
        }
        enposicio = false;
        notifyAll();
    }
    public void apuntar() {
        while (enposicio == true) {
            try { wait(); } catch (InterruptedException e) { }
        }
        enposicio = true;
        notifyAll();
    }
}

