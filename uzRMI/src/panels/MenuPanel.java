package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Client;

public class MenuPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton logIn;
	private JButton logOut;
	private JButton addProduct;
	private Client clientRef;

	public MenuPanel(Client clientRef) {
		logIn = new JButton("Log in");
		logOut = new JButton("Log out");
		addProduct = new JButton("Add product");
		this.clientRef = clientRef;

		logIn.addActionListener(this);
		logOut.addActionListener(this);
		addProduct.addActionListener(this);

		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(500, 200));
		
		add(logIn);
		add(logOut);
		add(addProduct);
		//if user==admin...
		logOut.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source == logIn)
			System.out.println("Logowanie");
		else if(source == logOut)
			System.out.println("Wylogowanie");
		else if(source == addProduct)
			System.out.println("dodawanie produktu");
	}

}
