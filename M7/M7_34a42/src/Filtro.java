import java.awt.*;

public abstract class Filtro {
    private String nombre;
    public Filtro(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    public abstract void aplicar(ImagenOF imagen);
}
