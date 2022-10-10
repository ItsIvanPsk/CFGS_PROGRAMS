import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

public class AdministradorDeArchivos {
    private static final String IMAGE_FORMAT = "jpg";

    public static ImagenOF loadImage(File imageFile)
    {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if(image == null || (image.getWidth(null) < 0)) {
                // we could not load the image - probably invalid file format
                System.out.println("return null");
                return null;
            }

            return new ImagenOF(image);
        }
        catch(IOException exc) {
            return null;
        }
    }
    public static void saveImage(ImagenOF image, File file)
    {
        try {
            ImageIO.write(image, IMAGE_FORMAT, file);
        }
        catch(IOException exc) {
            return;
        }
    }

}
