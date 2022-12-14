import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

public class VisorDeImagen {

    JLabel etiquetaEstado;
    JLabel etiquetaNombreDeArchivo;
    public static void main(String[] args) {
        VisorDeImagen visorDeImagen = new VisorDeImagen();
    }
    public VisorDeImagen() {
        construirVentana();
    }

    private void construirVentana() {
        JFrame ventana = new JFrame("Visor de Imagenes");

        Container panelContenedor = ventana.getContentPane();
        panelContenedor  =  ventana.getContentPane();

        panelContenedor.setLayout(new BorderLayout());

        Container panelDeImagen = new JPanel();
        panelContenedor.add(panelDeImagen, BorderLayout.CENTER);

        etiquetaNombreDeArchivo = new JLabel("JLabel - 1");
        panelContenedor.add(etiquetaNombreDeArchivo, BorderLayout.NORTH);

        etiquetaEstado  =  new  JLabel("JLabel - 2");
        panelContenedor.add(etiquetaEstado, BorderLayout.SOUTH);


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

        JMenu menuFiltro = new JMenu("Filtro");
        barraDeMenu.add(menuFiltro);
        JMenuItem elementoOscuro = new JMenuItem("Oscuro");
        menuArchivo.add(elementoOscuro);
        JMenuItem elementoUmbral = new JMenuItem("Umbral");
        menuArchivo.add(elementoUmbral);


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
        JOptionPane.showMessageDialog(null, "Ayuda!");
    }
    private void aplicarOscuro()
    {
        System.out.println("Oscuro.");
        /*
        if (imagenActual != null)
        {
            imagenActual.oscuro();
            vetana.repaint();
            mostrarEstado("Filtro aplicado: Oscuro");
        }
        else {
            mostrarEstado("No hay ninguna imagen cargada");
        }
         */

    }
    private void aplicarClaro()
    {
        System.out.println("Claro.");
    }
    private void aplicarUmbral()
    {
        System.out.println("Umbral.");
    }
    private void mostrarEstado(String str)
    {
        etiquetaEstado.setText(str);
    }


}