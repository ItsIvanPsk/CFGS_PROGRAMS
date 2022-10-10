import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

public class VisorDeImagen {
    PanelDeImagen panelDeImagen;
    JFrame ventana;
    JLabel etiquetaEstado;
    JLabel etiquetaNombreDeArchivo;
    ImagenOF currentImage;
    private List<Filtro> filtros;
    private static final String VERSION = "Version 2.0";
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
        listaDeFiltros.add(new FiltroOscuro("Claro"));
        listaDeFiltros.add(new FiltroGrises("Umbral"));
        listaDeFiltros.add(new FiltroEspejo("Espejo"));
        listaDeFiltros.add(new FiltroInvertir("Invertir"));
        listaDeFiltros.add(new FiltroAlisar("Alisar"));
        listaDeFiltros.add(new FiltroSolarizar("Solarizar"));
        listaDeFiltros.add(new FiltroBordes("Bordes"));
        listaDeFiltros.add(new FiltroOjoPez("Ojo de Pez"));

        return listaDeFiltros;
    }

    private void construirVentana() {
        ventana = new JFrame("Visor de Imagenes");

        Container panelContenedor = ventana.getContentPane();
        panelContenedor  =  ventana.getContentPane();

        panelContenedor.setLayout(new BorderLayout());

        panelDeImagen = new PanelDeImagen();
        panelContenedor.add(panelDeImagen);

        etiquetaNombreDeArchivo = new JLabel("JLabel - 1");
        panelContenedor.add(etiquetaNombreDeArchivo, BorderLayout.NORTH);

        etiquetaEstado  =  new  JLabel("JLabel - 2");
        panelContenedor.add(etiquetaEstado, BorderLayout.SOUTH);

        // building is done - arrange the components and show
        showFilename(null);
        ventana.pack();

        // center the frame on screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setLocation(d.width/2 - ventana.getWidth()/2, d.height/2 - ventana.getHeight()/2);
        ventana.setVisible(true);

        BarraDeMenu(ventana);
        ventana.pack();
        ventana.setSize(400, 400);
        ventana.setVisible(true);
    }
    public void BarraDeMenu(JFrame ventana) {

        JMenu menu;
        JMenuItem item;

        JMenuBar barraDeMenu = new JMenuBar();
        ventana.setJMenuBar(barraDeMenu);

        JMenu menuArchivo = new JMenu("Archivo");
        barraDeMenu.add(menuArchivo);
        JMenuItem elementoAbrir = new JMenuItem("Abrir");
        menuArchivo.add(elementoAbrir);
        JMenuItem elementoGrabar = new JMenuItem("Grabar");
        menuArchivo.add(elementoGrabar);
        JMenuItem elementoSalir = new JMenuItem("Salir");
        menuArchivo.add(elementoSalir);

        JMenu menuFiltro = new JMenu("Filtro");
        barraDeMenu.add(menuFiltro);

        for(final Filtro filter : filtros) {
            item = new JMenuItem(filter.getNombre());
            System.out.println(filter.getNombre());
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    applyFilter(filter);
                }
            });
            menuFiltro.add(item);
        }

        JMenu menuAyuda = new JMenu("Ayuda");
        barraDeMenu.add(menuAyuda);
        JMenuItem elementoAcercaDe = new JMenuItem("Acerca del Visor de Imagenes");

        elementoAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { abrirArchivo(); }
        });
        elementoGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { grabarArchivo(); }
        });
        elementoSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { salir(); }
        });
        elementoAcercaDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { acercaDe(); }
        });
        menuAyuda.add(elementoAcercaDe);
    }
    private void abrirArchivo() {
        int returnVal = fileChooser.showOpenDialog(ventana);

        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        currentImage = AdministradorDeArchivos.loadImage(selectedFile);

        if(currentImage == null) {   // image file was not a valid image
            JOptionPane.showMessageDialog(ventana,
                    "The file was not in a recognized image file format.",
                    "Image Load Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        panelDeImagen.setImage(currentImage);
        showFilename(selectedFile.getPath());
        showStatus("File loaded.");
        ventana.pack();
    }
    private void close()
    {
        currentImage = null;
        panelDeImagen.clearImage();
        showFilename(null);
    }
    private void applyFilter(Filtro filter)
    {
        if(currentImage != null) {
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

}