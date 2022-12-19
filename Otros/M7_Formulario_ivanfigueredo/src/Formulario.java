import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;

public class Formulario extends JFrame implements ActionListener {

	private JPanel bg;
	private JTextField input;
    JLabel label;
    JComboBox dropbox;
    JCheckBox checkbox;
    JRadioButton radioButton;
    private final Action action = new SwingAction();

	public static void main(String[] args) {
		Formulario form = new Formulario();
	}


	public Formulario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		
		bg = new JPanel();
		bg.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(bg);
		bg.setLayout(null);
		
		label = new JLabel("Nombre: ");
		label.setBounds(10, 11, 60, 14);
		bg.add(label);
		
		input = new JTextField();
		input.setBounds(96, 8, 125, 20);
		bg.add(input);
		input.setColumns(10);
		
		label = new JLabel("Ciudad: ");
		label.setBounds(10, 41, 46, 14);
		bg.add(label);
		
		label = new JLabel("Aficiones");
		label.setBounds(10, 84, 46, 14);
		bg.add(label);
		
		label = new JLabel("Género: ");
		label.setBounds(241, 84, 46, 14);
		bg.add(label);
		
		dropbox = new JComboBox();
		dropbox.setModel(new DefaultComboBoxModel(new String[] {"Escoja una ciudad", "Cornella de Llobregat", "San Boi de Llobregat", "San Feliu de Llobregat"}));
		dropbox.setBounds(96, 39, 125, 18);
		bg.add(dropbox);
		
		checkbox = new JCheckBox("Golf");
		checkbox.setBounds(10, 105, 97, 23);
		bg.add(checkbox);
		
		checkbox = new JCheckBox("Tenis");
		checkbox.setBounds(10, 149, 97, 23);
		bg.add(checkbox);
		
		checkbox = new JCheckBox("Padel");
		checkbox.setBounds(111, 105, 97, 23);
		bg.add(checkbox);
		
		checkbox = new JCheckBox("Petanca");
		checkbox.setBounds(111, 149, 97, 23);
		bg.add(checkbox);
		
		radioButton = new JRadioButton("Hombre");
		radioButton.setBounds(275, 105, 109, 23);
		bg.add(radioButton);
		
		radioButton = new JRadioButton("Mujer");
		radioButton.setBounds(275, 149, 109, 23);
		bg.add(radioButton);
		
		JButton enviarButton = new JButton("ENVIAR");
		enviarButton.setAction(action);
		enviarButton.setBounds(90, 227, 259, 23);
		bg.add(enviarButton);

		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO : a
		
	}
}
