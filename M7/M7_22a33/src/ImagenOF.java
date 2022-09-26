import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagenOF extends BufferedImage
{
    public ImagenOF(int width, int height, int imageType) {
        super(width, height, imageType);
    }


    public void setPixel(int x, int y, Color col)
    {
        int pixel = col.getRGB();
        setRGB(x, y, pixel);
    }
    public Color getPixel(int x, int y)
    {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }

    public void oscuro()
    {
        int alto = getHeight();
        int ancho = getWidth();
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++) {
                setPixel(x, y, getPixel(x, y).darker());
            }
        }
    }

    public void claro()
    {
        int alto = getHeight();
        int ancho = getWidth();
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++) {
                setPixel(x, y, getPixel(x, y).brighter());
            }
        }
    }

    public void umbral()
    {
        int alto = getHeight();
        int ancho = getWidth();
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++) {
                Color pixel = getPixel(x, y);
                int brillo = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                if(brillo <= 85) {
                    setPixel(x, y, Color.BLACK);
                }
                else if(brillo <= 170) {
                    setPixel(x, y, Color.GRAY);
                }
                else {
                    setPixel(x, y, Color.WHITE);
                }
            }
        }
    }
}
