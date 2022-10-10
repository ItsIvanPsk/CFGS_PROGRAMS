import java.awt.*;

public class FiltroInvertir extends Filtro{

    public FiltroInvertir(String nombre)
    {
        super(nombre);
    }

    public void aplicar(ImagenOF image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pix = image.getPixel(x, y);
                image.setPixel(x, y, new Color(255 - pix.getRed(),
                        255 - pix.getGreen(),
                        255 - pix.getBlue()));
            }
        }
    }
}
