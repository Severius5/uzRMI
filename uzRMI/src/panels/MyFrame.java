package panels;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Shop;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Shop netConn;
	
	public MyFrame(Shop netConn){
		super("Okienko");
		this.netConn = netConn;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 300);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel menuPanel = new MenuPanel(netConn);
		add(menuPanel);
		
		pack();
		
	}

}
