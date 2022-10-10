import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class PanelDeImagen extends JComponent {
    private int width, height;
    private ImagenOF panelImage;
    public PanelDeImagen()
    {
        width = 360;    // arbitrary size for empty panel
        height = 240;
        panelImage = null;
    }
    public void setImage(ImagenOF image)
    {
        if(image != null) {
            width = image.getWidth();
            height = image.getHeight();
            panelImage = image;
            repaint();
        }
    }

    public void clearImage()
    {
        if(panelImage != null) {
            Graphics imageGraphics = panelImage.getGraphics();
            imageGraphics.setColor(Color.LIGHT_GRAY);
            imageGraphics.fillRect(0, 0, width, height);
            repaint();
        }
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g)
    {
        Dimension size = getPreferredSize();
        g.clearRect(0, 0, size.width, size.height);
        if(panelImage != null) {
            g.drawImage(panelImage, 0, 0, null);
        }
    }

}
