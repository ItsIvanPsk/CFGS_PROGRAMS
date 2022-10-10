import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FiltroAlisar extends Filtro {

    public FiltroAlisar(String nombre)
    {
        super(nombre);
    }

    private ImagenOF original;
    private int width;
    private int height;

    public void aplicar(ImagenOF image)
    {
        original = new ImagenOF(image);
        width = original.getWidth();
        height = original.getHeight();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, smooth(x, y));
            }
        }
    }

    private Color smooth(int xpos, int ypos)
    {
        java.util.List<Color> pixels = new ArrayList<Color>(9);

        for(int y = ypos - 1; y <= ypos + 1; y++) {
            for(int x = xpos - 1; x <= xpos + 1; x++) {
                if( x >= 0 && x < width && y >= 0 && y < height )
                    pixels.add(original.getPixel(x, y));
            }
        }

        return new Color(avgRed(pixels), avgGreen(pixels), avgBlue(pixels));
    }

    private int avgRed(java.util.List<Color> pixels)
    {
        int total = 0;
        for(Color color : pixels) {
            total += color.getRed();
        }
        return total / pixels.size();
    }

    private int avgGreen(java.util.List<Color> pixels)
    {
        int total = 0;
        for(Color color : pixels) {
            total += color.getGreen();
        }
        return total / pixels.size();
    }

    private int avgBlue(List<Color> pixels)
    {
        int total = 0;
        for(Color color : pixels) {
            total += color.getBlue();
        }
        return total / pixels.size();
    }
}
