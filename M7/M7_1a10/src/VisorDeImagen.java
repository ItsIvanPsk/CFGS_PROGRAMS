import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VisorDeImagen {

    public static void main(String[] args) {
        VisorDeImagen visorDeImagen = new VisorDeImagen();
    }
    public VisorDeImagen() {
        construirVentana();
    }

    private void construirVentana() {
        JFrame ventana = new JFrame("Visor de Imagenes");

        Container panelContenedor = ventana.getContentPane();
        panelContenedor  =  ventana. getContentPane ();

        Container panelDeImagen = new JPanel();
        panelContenedor.add(panelDeImagen);

        JLabel etiquetaNombreDeArchivo = new JLabel("JLabel - 1");
        panelContenedor.add(etiquetaNombreDeArchivo);

        JLabel etiquetaEstado  =  new  JLabel("JLabel - 2");
        panelContenedor.add(etiquetaEstado);

        BarraDeMenu(ventana);
        ventana.pack();
        ventana.setSize(400, 400);
        ventana.setVisible(true);
    }
    public void BarraDeMenu(JFrame ventana) {
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
        System.out.println("Abrir.");
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
    }
}