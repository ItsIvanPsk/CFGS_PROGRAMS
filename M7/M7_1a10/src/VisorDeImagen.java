import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisorDeImagen extends JFrame implements ActionListener {

    private JFrame ventana;

    private JMenuBar barraDeMenu;
    private JMenu menuArchivo, menuAyuda;
    private JMenuItem elementoAbrir, elementoSalir, elementoEditar,elentoGrabar,  elementoAyuda;

    public VisorDeImagen() {
        ConstruirVentana();
    }
    public void ConstruirVentana()
    {
        ventana = new JFrame("Visor de Imagenes");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(10,10,500,400);
        ConstruirMenu();

    }

    public void ConstruirMenu()
    {
        barraDeMenu = new JMenuBar();
        ventana.setJMenuBar(barraDeMenu);
        ventana.add(barraDeMenu);

        setLayout(null);

        menuArchivo = new JMenu("Archivo");
        barraDeMenu.add(menuArchivo);

        elementoAbrir = new JMenuItem("Abrir");
        elementoEditar = new JMenuItem("Editar");
        menuArchivo.add(elementoAbrir);
        menuArchivo.add(elementoEditar);

        barraDeMenu = new JMenuBar();
        setJMenuBar(barraDeMenu);

        menuArchivo = new JMenu("Opciones");
        barraDeMenu.add(menuArchivo);

        menuAyuda = new JMenu("Ayuda");
        barraDeMenu.add(menuAyuda);

        elementoAbrir=new JMenuItem("Abrir");
        elementoAbrir.addActionListener(new AbrirActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                abrirArchivo();
            }
        });
        elementoAbrir.setActionCommand("Añadir");
        menuArchivo.add(elementoAbrir);

        elentoGrabar=new JMenuItem("Grabar");
        elentoGrabar.addActionListener(this);
        elentoGrabar.setActionCommand("Grabar");
        menuArchivo.add(elentoGrabar);

        elementoSalir=new JMenuItem("Salir");
        elementoSalir.addActionListener(new SalirActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                salir();
            }
        });        elementoSalir.setActionCommand("Salir");
        menuArchivo.add(elementoSalir);

        elementoAyuda=new JMenuItem("Acerca del Visor de Imagenes");
        elementoAyuda.addActionListener(this);
        elementoAyuda.setActionCommand("Ayuda");
        menuAyuda.add(elementoAyuda);
    }

    public static void main(String[] args) {

        VisorDeImagen visor = new VisorDeImagen();
        visor.setVisible(true);
    }


    private void grabarArchivo() {
        System.out.println("Grabar");
    }

    private void salir() {
        System.out.println("Salir");
        System.exit(0);
    }

    private void abrirArchivo() {
        System.out.println("Abrir");
    }

    private void ayuda() {
        System.out.println("Ayuda");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    class AbrirActionListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            abrirArchivo();
        }
    }
    class SalirActionListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            salir();
        }
    }


}

/*

 */