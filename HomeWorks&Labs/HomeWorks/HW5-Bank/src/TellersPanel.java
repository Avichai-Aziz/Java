import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TellersPanel extends JPanel implements ActionListener{
	/**
	 * This class represents the panel that each teller gets inside the bank display
	 * in order to allow and refresh the tellers statues (teller number, customer served number, isIdle)
	 * each teller gets his own panel.
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton idleButton;
	
	private JLabel servingLabel;
	
	private Teller teller;
	
	public TellersPanel(Teller teller) {
		this.teller = teller;
		
		this.setPreferredSize(new Dimension(400,60));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
		this.idleButton = new JButton("idle");
		this.servingLabel = new JLabel("");
		
		this.add(idleButton);
		this.add(servingLabel);
		
		this.idleButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == idleButton) { // action for the goIdle flag, in order to make the teller idle.
			this.teller.goIdle();
			idleButton.setEnabled(false);
		}
	}
	
	public void tellerPanelRefresh() { // refreshing he tellers panel , called by refresh @ the bank display class. 
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Sample: Teller " + teller.getTellerNumber() + " is ");
		if (teller.isIdle()) {
			stringBuffer.append("idle");
		}
		else {
			stringBuffer.append("active");
			if (teller.isServing()) {
				stringBuffer.append(", currently serving customer " + teller.getCustNumber());
			}
		}
		if (!(this.teller.isIdle())) {
			idleButton.setEnabled(true);
		}
		this.servingLabel.setText(stringBuffer.toString());
	}
}
