import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisorDeImagen extends JFrame implements ActionListener {

    private JFrame ventana;

    private JMenuBar barraDeMenu;
    private JMenu menuArchivo, menuAyuda;
    private JMenuItem elementoAñadir, elementoEditar, elentoGrabar,  elementoAyuda;

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

        elementoAñadir = new JMenuItem("Abrir");
        elementoEditar = new JMenuItem("Editar");
        menuArchivo.add(elementoAñadir);
        menuArchivo.add(elementoEditar);

        barraDeMenu = new JMenuBar();
        setJMenuBar(barraDeMenu);

        menuArchivo = new JMenu("Opciones");
        barraDeMenu.add(menuArchivo);

        menuAyuda = new JMenu("Ayuda");
        barraDeMenu.add(menuAyuda);

        elementoAñadir=new JMenuItem("Añadir");
        elementoAñadir.addActionListener(this);
        elementoAñadir.setActionCommand("Añadir");
        menuArchivo.add(elementoAñadir);

        elentoGrabar=new JMenuItem("Grabar");
        elentoGrabar.addActionListener(this);
        elentoGrabar.setActionCommand("Grabar");
        menuArchivo.add(elentoGrabar);

        elementoEditar=new JMenuItem("Salir");
        elementoEditar.addActionListener(this);
        elementoEditar.setActionCommand("Salir");
        menuArchivo.add(elementoEditar);

        elementoAyuda=new JMenuItem("Acerca del Visor de Imagenes");
        elementoAyuda.addActionListener(this);
        elementoAyuda.setActionCommand("Ayuda");
        menuAyuda.add(elementoAyuda);
    }

    public static void main(String[] args) {

        VisorDeImagen visor = new VisorDeImagen();
        visor.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Añadir"))
            abrirArchivo();
        else if (comando.equals("Grabar"))
            grabarArchivo();
        else if (comando.equals("Salir"))
            salir();
        else if (comando.equals("Ayuda"))
            ayuda();
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

}

/*

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class VisorDeImagen extends JFrame implements ActionListener{
    private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem mi1,mi2,mi3;
    public VisorDeImagen() {
        setLayout(null);
        mb=new JMenuBar();
        setJMenuBar(mb);
        menu1=new JMenu("Opciones");
        mb.add(menu1);
        mi1=new JMenuItem("Rojo");
        mi1.addActionListener(this);
        menu1.add(mi1);
        mi2=new JMenuItem("Verde");
        mi2.addActionListener(this);
        menu1.add(mi2);
        mi3=new JMenuItem("Azul");
        mi3.addActionListener(this);
        menu1.add(mi3);
    }

    public void actionPerformed(ActionEvent e) {
        Container f=this.getContentPane();
        if (e.getSource()==mi1) {
            f.setBackground(new Color(255,0,0));
        }
        if (e.getSource()==mi2) {
            f.setBackground(new Color(0,255,0));
        }
        if (e.getSource()==mi3) {
            f.setBackground(new Color(0,0,255));
        }
    }

    public static void main(String[] ar) {
        VisorDeImagen formulario1=new VisorDeImagen();

    }
}

 */