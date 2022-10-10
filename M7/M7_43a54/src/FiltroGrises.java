import java.awt.*;

public class FiltroGrises extends Filtro{
    public FiltroGrises(String nombre){
        super(nombre);
    }

    @Override
    public void aplicar(ImagenOF imagen) {
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++) {
                Color pixel = imagen.getPixel(x, y);
                int brillo = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                if(brillo <= 85) {
                    imagen.setPixel(x, y, Color.BLACK);
                }
                else if(brillo <= 170) {
                    imagen.setPixel(x, y, Color.GRAY);
                }
                else {
                    imagen.setPixel(x, y, Color.WHITE);
                }
            }
        }
    }
}