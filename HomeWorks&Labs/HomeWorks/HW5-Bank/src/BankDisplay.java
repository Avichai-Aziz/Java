import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BankDisplay extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bank bank;
	private JPanel centerPanel;
	private JPanel southPanel;
	
	private ArrayList<TellersPanel> panels;

	private JLabel customersWaitListLabel;

	public BankDisplay(Bank bank) {
		this.bank = bank;
		this.setName("Bank");
		this.setPreferredSize((new Dimension(600,600)));

		Dimension centerPanelD = new Dimension(250,250);
		Dimension southPanelD = new Dimension(100,100);

		this.centerPanel = new JPanel();
		this.southPanel = new JPanel();

		this.setLayout(new BorderLayout());

		this.centerPanel.setPreferredSize(centerPanelD);
		this.centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				
		this.panels = new ArrayList<TellersPanel>();

		for(Teller teller : bank.getTellers()) {
			TellersPanel tp = new TellersPanel(teller);
			this.panels.add(tp);
			this.centerPanel.add(tp);
		}
		
		this.southPanel.setPreferredSize(southPanelD);
		this.southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		this.southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.customersWaitListLabel = new JLabel(bank.getCustomersQueue().toString());
		this.southPanel.add(customersWaitListLabel);
		
		this.add(centerPanel, BorderLayout.CENTER); 
		this.add(southPanel,BorderLayout.SOUTH);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	public void refresh() {
		for (TellersPanel tPanel : this.panels) {
			tPanel.tellerPanelRefresh();
		}
		this.customersWaitListLabel.setText(bank.getCustomersQueue().toString());
	}

	public void close()
	{
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}



