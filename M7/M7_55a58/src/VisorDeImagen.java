import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;

public class VisorDeImagen {
    static PanelDeImagen panelDeImagen = new PanelDeImagen();
    JFrame ventana;
    static JLabel etiquetaEstado;
    static JLabel etiquetaNombreDeArchivo;
    static File initialImage;
    static ImagenOF currentImage;
    static ImagenOF lastSavedImage;

    JButton smallerButton;
    JButton largerButton;

    JMenu jm_edit;
    JMenu jm_filtro;
    JMenu jm_ajustar;

    private List<Filtro> filtros;

    private static ArrayList<Object> buttons = new ArrayList<>();

    private static final String VERSION = "Version 2.1";
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    public static void main(String[] args) {
        VisorDeImagen visorDeImagen = new VisorDeImagen();
    }
    public VisorDeImagen() {
        currentImage = null;
        filtros = crearFiltros();
        construirVentana();
    }

    private List<Filtro> crearFiltros() {
        List<Filtro> listaDeFiltros = new ArrayList<Filtro>();
        listaDeFiltros.add(new FiltroOscuro("Oscuro"));
        listaDeFiltros.add(new FiltroClaro("Claro"));
        listaDeFiltros.add(new FiltroGrises("Umbral"));
        listaDeFiltros.add(new FiltroEspejo("Espejo"));
        listaDeFiltros.add(new FiltroInvertir("Invertir"));
        listaDeFiltros.add(new FiltroAlisar("Alisar"));
        listaDeFiltros.add(new FiltroSolarizar("Solarizar"));
        listaDeFiltros.add(new FiltroBordes("Bordes"));
        listaDeFiltros.add(new FiltroOjoPez("Ojo de Pez"));

        return listaDeFiltros;
    }

    private void construirVentana()
    {
        ventana = new JFrame("ImageViewer");
        JPanel contentPane = (JPanel)ventana.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        construirMenu(ventana);

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the image pane in the center
        panelDeImagen.setBorder(new EtchedBorder());
        contentPane.add(panelDeImagen, BorderLayout.CENTER);

        // Create two labels at top and bottom for the file name and status messages
        etiquetaNombreDeArchivo = new JLabel();
        contentPane.add(etiquetaNombreDeArchivo, BorderLayout.NORTH);

        etiquetaEstado = new JLabel(VERSION);
        contentPane.add(etiquetaEstado, BorderLayout.SOUTH);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0,1));

        smallerButton = new JButton("Smaller");
        smallerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  { empequeñecer(); }
        });
        toolbar.add(smallerButton);

        largerButton = new JButton("Larger");
        largerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { agrandar(); }
        });
        toolbar.add(largerButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);

        contentPane.add(flow, BorderLayout.WEST);

        // building is done - arrange the components
        showFilename(null);
        setButtonsEnabled(false);
        ventana.pack();

        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setLocation(d.width/2 - ventana.getWidth()/2, d.height/2 - ventana.getHeight()/2);
        ventana.setVisible(true);
    }
    private void construirMenu(JFrame frame)
    {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;

        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);

        item = new JMenuItem("Open...");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { abrirArchivo(); }
        });
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Close");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { close(); }
        });
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("Save As...");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { grabarArchivo(); }
        });
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { salir(); }
        });
        menu.add(item);

        // create the Edit menu
        jm_edit = new JMenu("Edit");
        item = new JMenuItem("Deshacer");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { deshacer(); }
        });
        jm_edit.add(item);

        menubar.add(jm_edit);

        // create the Filter menu
        jm_filtro = new JMenu("Filter");
        menubar.add(jm_filtro);

        for(final Filtro filter : filtros) {
            item = new JMenuItem(filter.getNombre());
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    applyFilter(filter);
                }
            });
            jm_filtro.add(item);
        }

        // create the Ajustar menu
        jm_ajustar = new JMenu("Ajustar");
        menubar.add(jm_ajustar);

        item = new JMenuItem("Rotar");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImagenOF imgof = new ImagenOF(rotateImage(currentImage));
                panelDeImagen.setImage(imgof);
            }
        });
        jm_ajustar.add(item);
        menubar.add(jm_ajustar);

        item = new JMenuItem("Deshacer todo");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { deshacerTodos(); }
        });
        jm_ajustar.add(item);
        menubar.add(jm_ajustar);

        menu = new JMenu("Help");
        item = new JMenuItem("About ImageViewer...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { mostrarAcerdaDe(); }
        });
        menu.add(item);
        menubar.add(menu);

    }

    public static BufferedImage rotateImage(BufferedImage imageToRotate) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(90), widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }

    private static void deshacer() {
        AdministradorDeArchivos.loadImage(initialImage);
        panelDeImagen.setImage(currentImage);
        etiquetaEstado.setText("Se ha restablecido la imagen a su anterior cambio");
    }

    private void deshacerTodos() {
        this.currentImage = AdministradorDeArchivos.loadImage(initialImage);
        panelDeImagen.setImage(currentImage);
        etiquetaEstado.setText("Se ha restablecido la imagen a su ultimo guardado");
    }

    private void abrirArchivo() {
        int returnVal = fileChooser.showOpenDialog(ventana);

        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return;  // cancelled
        }
        initialImage = fileChooser.getSelectedFile();
        currentImage = AdministradorDeArchivos.loadImage(initialImage);

        if(currentImage == null) {   // image file was not a valid image
            JOptionPane.showMessageDialog(ventana,
                    "The file was not in a recognized image file format.",
                    "Image Load Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        panelDeImagen.setImage(currentImage);
        showFilename(initialImage.getPath());
        showStatus("File loaded.");
        setButtonsEnabled(true);
        ventana.pack();
    }
    private void close()
    {
        setButtonsEnabled(false);
        currentImage = null;
        panelDeImagen.clearImage();
        showFilename(null);
    }
    private void applyFilter(Filtro filter)
    {
        if(currentImage != null) {
            lastSavedImage = currentImage;
            filter.aplicar(currentImage);
            ventana.repaint();
            showStatus("Applied: " + filter.getNombre());
        }
        else {
            showStatus("No image loaded.");
        }
    }

    private void showFilename(String filename)
    {
        if(filename == null) {
            etiquetaNombreDeArchivo.setText("No file displayed.");
        }
        else {
            etiquetaNombreDeArchivo.setText("File: " + filename);
        }
    }

    private void showStatus(String text)
    {
        etiquetaEstado.setText(text);
    }

    private void grabarArchivo() {
        System.out.println("Grabar");
    }
    private void salir() {
        System.out.println("Salir");
        System.exit(0);
    }
    private void acercaDe() {
        System.out.println("Ayuda");
        mostrarAcerdaDe();
    }
    private void aplicarOscuro(ImagenOF image)
    {
        System.out.println("Oscuro.");
        filtros.get(0).aplicar(image);
    }
    private void aplicarClaro(ImagenOF image)
    {
        System.out.println("Claro.");
        filtros.get(1).aplicar(image);
    }

    private void aplicarUmbral(ImagenOF image)
    {
        System.out.println("Umbral.");
        filtros.get(2).aplicar(image);
    }
    private void mostrarEstado(String str)
    {
        etiquetaEstado.setText(str);
    }

    private void mostrarAcerdaDe(){
        JOptionPane.showMessageDialog(ventana,
                "Visor de Imagenes\n" + VERSION,
                "Acerca del Visor de Imagenes",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void agrandar()
    {
        if(currentImage != null) {
            // create new image with double size
            int width = currentImage.getWidth();
            int height = currentImage.getHeight();
            ImagenOF newImage = new ImagenOF(width * 2, height * 2);

            // copy pixel data into new image
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    Color col = currentImage.getPixel(x, y);
                    newImage.setPixel(x * 2, y * 2, col);
                    newImage.setPixel(x * 2 + 1, y * 2, col);
                    newImage.setPixel(x * 2, y * 2 + 1, col);
                    newImage.setPixel(x * 2+1, y * 2 + 1, col);
                }
            }

            currentImage = newImage;
            panelDeImagen.setImage(currentImage);
            ventana.pack();
        }
    }

    private void empequeñecer()
    {
        if(currentImage != null) {
            // create new image with double size
            int width = currentImage.getWidth() / 2;
            int height = currentImage.getHeight() / 2;
            ImagenOF newImage = new ImagenOF(width, height);

            // copy pixel data into new image
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    newImage.setPixel(x, y, currentImage.getPixel(x * 2, y * 2));
                }
            }

            currentImage = newImage;
            panelDeImagen.setImage(currentImage);
            ventana.pack();
        }
    }

    private void setButtonsEnabled(boolean status)
    {
        smallerButton.setEnabled(status);
        largerButton.setEnabled(status);
        jm_filtro.setEnabled(status);
        jm_edit.setEnabled(status);
        jm_ajustar.setEnabled(status);
    }

}