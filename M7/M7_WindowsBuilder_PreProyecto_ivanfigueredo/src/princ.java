import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.Button;
import java.awt.Label;
import javax.swing.SpinnerListModel;

public class princ extends JFrame implements ActionListener, ItemListener  {

	private JPanel contentPane;
	private JButton okBtn;
	private Button button;
	private JSlider slider;
	private JSpinner spinner;
	private JToggleButton tglbtnNewToggleButton;
	private Label label_1;
	private Label label;
	private Button button_1;
	private Button button_2;
	private Label label_2;
	
	private File file;
	
	private String[] strs = new String[3];

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
		Font font = new Font("Serif", Font.ITALIC, 15);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tglbtnNewToggleButton = new JToggleButton("ON");
		tglbtnNewToggleButton.setBounds(138, 9, 60, 23);
		tglbtnNewToggleButton.addItemListener(this);  
		contentPane.add(tglbtnNewToggleButton);
		spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		spinner.setBounds(138, 45, 70, 23);
		contentPane.add(spinner);
		
		slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setValue(1000);
		slider.setMaximum(5000);
		slider.setMinimum(1000);
		slider.setBounds(8, 99, 200, 26);
		contentPane.add(slider);
		
		button = new Button("Open File");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					    "TXT files", "txt");
					fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(contentPane);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						file = new File(fc.getSelectedFile().getAbsolutePath());
					    Scanner myReader = new Scanner(file);
					    int i = 0;
					    while (myReader.hasNextLine()) {
					    	strs[i] = myReader.nextLine();
					    	
					        i++;
					    }
					    System.out.println("Open:\n" + strs[0] + " - " + strs[1] + " - " + strs[2] + " - ");
					    
					    updateUI();
					    myReader.close();
					    } catch (FileNotFoundException e2) {
					    	System.out.println("An error occurred.");
					    	e2.printStackTrace();
					    }
					}
				
			}
		});
		button.setBounds(418, 33, 70, 22);
		contentPane.add(button);
		
		label = new Label("Sistema de motores: ");
		label.setBounds(10, 10, 121, 22);
		contentPane.add(label);
		
		label_1 = new Label("Motores encendidos: ");
		label_1.setBounds(10, 46, 121, 22);
		contentPane.add(label_1);
		
		button_1 = new Button("Save File");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveUI();
			}
		});
		
		button_1.setBounds(418, 61, 70, 22);
		contentPane.add(button_1);
		
		label_2 = new Label("RPM Maquina");
		label_2.setBounds(70, 74, 70, 22);
		contentPane.add(label_2);
		
		okBtn = new JButton("OK");
		okBtn.setBounds(335, 227, 89, 23);
	}

	public void updateUI() {
		tglbtnNewToggleButton.setSelected(Boolean.parseBoolean(strs[0]));
		spinner.setValue(strs[1]);
		slider.setValue(Integer.parseInt(strs[2]));
		
	}
	
	public void refreshUI() {
		if(tglbtnNewToggleButton.isSelected()) {
			strs[0] = "true";
		} else { strs[0] = "false";}
		strs[1] = spinner.getValue().toString();
		Integer aux = slider.getValue();
		strs[2] = aux.toString();

		System.out.println("Open:\n" + strs[0] + " - " + strs[1] + " - " + strs[2] + " - ");

	}
	
	public void saveUI() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "TXT files", "txt");
			fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(contentPane);
	
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				refreshUI();
				FileWriter myWriter = new FileWriter(fc.getSelectedFile().getAbsolutePath());
				myWriter.write(strs[0] + "\n" + strs[1] + "\n" + strs[2]);
			    System.out.println("Open:\n" + strs[0] + " - " + strs[1] + " - " + strs[2] + " - ");

			    myWriter.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

	    int result = fileChooser.showOpenDialog(this);

	    if (result != JFileChooser.CANCEL_OPTION) {

	        File fileName = fileChooser.getSelectedFile();

	        if ((fileName == null) || (fileName.getName().equals(""))) {
	            okBtn.setText("...");
	        } else {
	        	okBtn.setText(fileName.getAbsolutePath());
	        }
	    }
	}

	private void setJToggleButton() {  
		tglbtnNewToggleButton = new JToggleButton("ON");  
        getContentPane().add(button);  
    }  
    private void setAction() {  
    	tglbtnNewToggleButton.addItemListener(this);  
    }  
    public void itemStateChanged(ItemEvent eve) {  
        if (tglbtnNewToggleButton.isSelected())  
        	tglbtnNewToggleButton.setText("ON");  
        else  
        	tglbtnNewToggleButton.setText("OFF");  
    }  
	
}
