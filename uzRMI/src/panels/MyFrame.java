package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Client;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MyFrame(Client clientRef){
		super("Sklep");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setPreferredSize(new Dimension(600, 400));
		
		JPanel cartList = new CartList(clientRef);
		JPanel itemList = new ItemList(clientRef, cartList);
		JPanel menuPanel = new MenuPanel(clientRef, itemList);
		
		setLayout(new FlowLayout());
		add(menuPanel);
		add(itemList);
		add(cartList);
		
		pack();
		
	}

}
