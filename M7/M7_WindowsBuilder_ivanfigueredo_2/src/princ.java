import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class princ extends JFrame {

	private JPanel contentPane;
	private JButton okBtn;
	private JCheckBox chckbxNewCheckBox;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					princ frame = new princ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public princ() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		okBtn = new JButton("OK");
		okBtn.setBounds(335, 227, 89, 23);
		
		contentPane.add(okBtn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.removeItem("1");
		comboBox.setBounds(10, 11, 282, 23);
		contentPane.add(comboBox);
		
		chckbxNewCheckBox = new JCheckBox("Pregunta");
		chckbxNewCheckBox.setSelected(false);
		chckbxNewCheckBox.setBounds(20, 41, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		ButtonGroup group = new ButtonGroup();
			group.add(rdbtnNewRadioButton);
			group.add(rdbtnNewRadioButton_1);

		
		rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(10, 67, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(10, 92, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(chckbxNewCheckBox.isSelected());
			}
		});
		
	}
}
