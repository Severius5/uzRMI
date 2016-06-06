package panels;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Shop;
import main.Client;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Client clientRef;
	
	public MyFrame(Client clientRef){
		super("Okienko");
		this.clientRef = clientRef;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 300);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel menuPanel = new MenuPanel(clientRef);
		add(menuPanel);
		
		pack();
		
	}

}
