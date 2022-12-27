import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;

public class Formulario extends JFrame implements ActionListener {

	private JPanel bg;
	private JTextField inputName;
    JLabel label;
    JComboBox dropbox;
    JCheckBox hobbie_1, hobbie_2, hobbie_3, hobbie_4;
    JRadioButton radioButton_1, radioButton_2;

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
		
		inputName = new JTextField();
		inputName.setBounds(96, 8, 125, 20);
		bg.add(inputName);
		inputName.setColumns(10);
		
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
		
		hobbie_1 = new JCheckBox("Golf");
		hobbie_1.setBounds(10, 105, 97, 23);
		bg.add(hobbie_1);
		
		hobbie_2 = new JCheckBox("Tenis");
		hobbie_2.setBounds(10, 149, 97, 23);
		bg.add(hobbie_2);
		
		hobbie_3 = new JCheckBox("Padel");
		hobbie_3.setBounds(111, 105, 97, 23);
		bg.add(hobbie_3);
		
		hobbie_4 = new JCheckBox("Petanca");
		hobbie_4.setBounds(111, 149, 97, 23);
		bg.add(hobbie_4);
		
		radioButton_1 = new JRadioButton("Hombre");
		radioButton_1.setBounds(275, 105, 109, 23);
		bg.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("Mujer");
		radioButton_2.setBounds(275, 149, 109, 23);
		bg.add(radioButton_2);
		
		JButton enviarButton = new JButton("ENVIAR");
        enviarButton.setActionCommand ("enviarButton");
		enviarButton.setBounds(90, 227, 259, 23);
        enviarButton.addActionListener(this);
		bg.add(enviarButton);

		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        if ("enviarButton".equals(e.getActionCommand())) {
            String name = inputName.getText();
            String city = dropbox.getSelectedItem().toString();
            ArrayList<String> hobbies = new ArrayList<>();
            String gender = "";
            if(radioButton_1.isSelected()) { gender = "Hombre"; }
            if(radioButton_2.isSelected()) { gender = "Mujer"; }

            if(hobbie_1.isSelected() == true){ hobbies.add("Golf"); }
            if(hobbie_2.isSelected() == true){ hobbies.add("Tenis"); }
            if(hobbie_3.isSelected() == true){ hobbies.add("Padel"); }
            if(hobbie_4.isSelected() == true){ hobbies.add("Petanca"); }

            if(name.equals("" )){ showDialog();}
            else if (city == "Escoja una ciudad"){ showDialog(); }
            else if(radioButton_1.isSelected() && radioButton_2.isSelected()) { showDialog(); }
            else {
                ResultFrame(name, city, hobbies, gender);
            }
        }
	}

    private void showDialog() {
        JOptionPane.showMessageDialog(this,
            "Faltan campos por rellenar",
            "Aviso.",
            JOptionPane.WARNING_MESSAGE);
    }
}
class ResultFrame extends JFrame {

    private JPanel bg;
    private JLabel name, city, hobbies, gender;
    public ResultFrame(String _name, String _city, ArrayList<String> _hobbies, String _gender) {
		// setDefaultCloseOperation(this.setVisible(false));
		setBounds(100, 100, 450, 300);
		
		
		bg = new JPanel();
		bg.setBorder(new EmptyBorder(5, 5, 5, 5));

        name = new JLabel();
        name.setText(_name);
        city = new JLabel();
        city.setText(_city);
        
        String hobbie = "";
        for (int i = 0; i < _hobbies.size(); i++) {
            hobbie = hobbie + " - " + _hobbies.get(i);
        }
        hobbies.setText(hobbie);
        gender.setText(_gender);

        bg.add(name);
        bg.add(city);
        bg.add(hobbies);
        bg.add(gender);

		setContentPane(bg);
    }
}
