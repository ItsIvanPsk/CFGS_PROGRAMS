limport java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTree;
import javax.swing.JTable;

public class princ extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTable table;

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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 11, 260, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Ok");

		btnNewButton.setBounds(20, 223, 89, 23);
		contentPane.add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 0, 100, 1));
		spinner.setBounds(10, 42, 140, 20);
		contentPane.add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 140, 139);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JTree tree = new JTree();
		tree.setBounds(160, 42, 83, 170);
		contentPane.add(tree);
		
		table = new JTable();
		table.setBounds(280, 11, 140, 49);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Nombre", "DNI", "Objeto"}
		));
		contentPane.add(table);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Btn pressed");
				System.out.println("Item Selected: " + list.getSelectedIndex());
				System.out.println("Row Count: " + tree.getRowCount());
				System.out.println("Column count: " + table.getColumnCount());
				
				int numCols = table.getModel().getColumnCount();
				Object[] fila = new Object[numCols];
				fila[0] = "123";
				fila[1] = "123";
				fila[2] = "Objeto";
				((DefaultTableModel) table.getModel()).addRow(fila);
				
				System.out.println((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
			} 
		});
		
	}
}
