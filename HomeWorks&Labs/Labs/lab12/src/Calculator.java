
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener{
	//fields
	private JFrame frame;

	private JPanel panelNorth;
	private JPanel panelSouth;
	private JPanel panelEast;
	private JPanel panelWest;
	private JPanel panelCenter;

	private JButton buttonAdd;
	private JButton buttonClear;
	private JButton buttonExit;
	private JButton buttonSubtract;


	private JTextField textFieldFirstNumber;
	private JTextField textFieldSecondNumber;
	private JTextField textFieldResult;

	private JLabel labelFirstNumber;
	private JLabel labelSecondNumber;
	private JLabel labelResult;
	
	public Calculator() {

		this.frame = new JFrame("Adder");
		this.frame.setSize(500, 500);
		/*
		 * sizes of the panels
		 */
		Dimension centerP = new Dimension(260,200);
		Dimension northP = new Dimension(30,30);
		Dimension southP = new Dimension(100,50);
		Dimension eastP = new Dimension(30,30);
		Dimension westP = new Dimension(30,30);
		/*
		 *divide the frame to 5 parts 
		 */
		this.frame.setLayout(new BorderLayout());
		/*
		 * label and text field - first number
		 */
		this.labelFirstNumber = new JLabel("First number:");
		this.textFieldFirstNumber = new JTextField(15);
		this.textFieldFirstNumber.setHorizontalAlignment(JTextField.RIGHT);
		this.textFieldFirstNumber.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		/*
		 * label and text field - second number
		 */
		this.labelSecondNumber = new JLabel("Second number:");
		this.textFieldSecondNumber = new JTextField(15);
		this.textFieldSecondNumber.setHorizontalAlignment(JTextField.RIGHT);
		this.textFieldSecondNumber.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		/*
		 * label and text field - result
		 */
		this.labelResult = new JLabel("Result:");
		this.textFieldResult = new JTextField(15);
		this.textFieldResult.setHorizontalAlignment(JTextField.RIGHT);
		this.textFieldResult.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		this.textFieldResult.setEditable(false);
		/*
		 * add button
		 */
		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setHorizontalAlignment(JButton.RIGHT);

		/*
		 * clear button
		 */
		this.buttonClear = new JButton("Clear");
		this.buttonClear.setHorizontalAlignment(JButton.RIGHT);
		/*
		 * exit button
		 */
		this.buttonExit = new JButton("Exit");
		this.buttonExit.setHorizontalAlignment(JButton.RIGHT);
		/*
		 * subtraction button
		 */
		this.buttonSubtract = new JButton("Subtract");
		this.buttonSubtract.setHorizontalAlignment(JButton.RIGHT);
		/*
		 * panel center
		 */
		this.panelCenter = new JPanel();
		this.panelCenter.setPreferredSize(centerP);
		this.panelCenter.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.panelCenter.setBorder(BorderFactory.createLineBorder(Color.RED,7));
		this.panelCenter.add(this.labelFirstNumber);
		this.panelCenter.add(this.textFieldFirstNumber);
		this.panelCenter.add(this.labelSecondNumber);
		this.panelCenter.add(this.textFieldSecondNumber);
		this.panelCenter.add(this.labelResult);
		this.panelCenter.add(this.textFieldResult);
		this.panelCenter.add(this.buttonAdd);
		this.panelCenter.add(this.buttonSubtract);
		this.panelCenter.add(this.buttonClear);
		/*
		 * panel south
		 */
		this.panelSouth = new JPanel();
		this.panelSouth.setPreferredSize(southP);
		this.panelSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.panelSouth.add(this.buttonExit);
		/*
		 * panel north, panel east, panel west
		 */
		this.panelNorth = new JPanel();
		this.panelNorth.setPreferredSize(northP);
		this.panelEast = new JPanel();
		this.panelEast.setPreferredSize(eastP);
		this.panelWest = new JPanel();	
		this.panelWest.setPreferredSize(westP);
		/*
		 * frame
		 */
		this.frame.add(this.panelNorth, BorderLayout.NORTH);
		this.frame.add(this.panelCenter, BorderLayout.CENTER);
		this.frame.add(this.panelEast, BorderLayout.EAST);
		this.frame.add(this.panelWest, BorderLayout.WEST);
		this.frame.add(this.panelSouth, BorderLayout.SOUTH);
		this.frame.pack();
		this.frame.setVisible(true);
		this.frame.setResizable(false); 
		/*
		 * action the buttons
		 */
		this.buttonAdd.addActionListener(this);
		this.buttonClear.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonSubtract.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if(e.getSource() == buttonExit) { //if press in exit button
				System.exit(1);
			}
			else if(e.getSource() == buttonAdd) { //if press in add button
				setValue(this.textFieldResult, getValue(this.textFieldFirstNumber) + getValue(this.textFieldSecondNumber));
			}
			else if(e.getSource() == buttonClear) { //if press in clear button
				this.textFieldFirstNumber.setText("");
				this.textFieldSecondNumber.setText("");
				this.textFieldResult.setText("");
			}
			else if(e.getSource() == buttonSubtract) {
				setValue(this.textFieldResult, getValue(this.textFieldFirstNumber) - getValue(this.textFieldSecondNumber));
			}
		} //end try
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(frame,"Non-numeric input, lease re-enter","input error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public double getValue(JTextField value) {
		return Double.parseDouble(value.getText());
	}
	public void setValue(JTextField value, double sum) {
		value.setText(Double.toString(sum));
	}
}
