import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VisorDeImagen {
    private JFrame ventana;

    public VisorDeImagen() {
        construirVentana();
    }

    private void construirVentana() {
        ventana = new JFrame("Visor de Imagenes");

        Container panelContenedor = ventana.getContentPane();
        panelContenedor  =  ventana.getContentPane();

        JLabel etiquetaNombreDeArchivo = new JLabel("wrngwalenfweinfowienfwoienfowienfowienf");
        panelContenedor.add(etiquetaNombreDeArchivo);

        Container panelDeImagen = new JPanel();
        panelContenedor.add(panelDeImagen);

        JLabel etiquetaEstado  =  new  JLabel("Version  1 .O");
        panelContenedor.add(etiquetaEstado);

        construirBarraDeMenu(ventana);
        ventana.pack();
        ventana.setSize(400, 400);
        ventana.setVisible(true);
    }
    public void construirBarraDeMenu(JFrame ventana) {
        JMenuBar barraDeMenu = new JMenuBar();
        ventana.setJMenuBar(barraDeMenu);

        JMenu menuArchivo = new JMenu("Archivo");
        barraDeMenu.add(menuArchivo);

        JMenuItem elementoAbrir = new JMenuItem("Abrir");
        elementoAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { abrirArchivo(); }
        });
        menuArchivo.add(elementoAbrir);

        JMenuItem elementoGrabar = new JMenuItem("Grabar");
        elementoGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { grabarArchivo(); }
        });
        menuArchivo.add(elementoGrabar);

        JMenuItem elementoSalir = new JMenuItem("Salir");
        elementoSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { salir(); }
        });

        menuArchivo.add(elementoSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        barraDeMenu.add(menuAyuda);

        JMenuItem elementoAcercaDe = new JMenuItem("Acerca del Visor de Imagenes");
        elementoAcercaDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { acercaDe(); }
        });
        menuAyuda.add(elementoAcercaDe);
    }
    private void abrirArchivo() {
        System.out.println("Has seleccionado el item: Abrir.");
    }
    private void grabarArchivo() {
        System.out.println("Has seleccionado el item: Grabar.");
    }
    private void salir() {
        System.out.println("Has seleccionado el item: Salir.");
    }
    private void acercaDe() {
        System.out.println("Has seleccionado el item: Acerca del Visor de ImÃ¡genes.");
    }
}