package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.Product;
import logic.ShopImp;
import main.Client;

public class MenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton logIn;
	private JButton logOut;
	private JButton addProduct;
	private JButton addNewProduct;
	private Client clientRef;

	public MenuPanel(Client clientRef) {
		logIn = new JButton("Log in");
		logOut = new JButton("Log out");
		addProduct = new JButton("Add product");
		addNewProduct = new JButton("Add new product");
		this.clientRef = clientRef;

		logIn.addActionListener(this);
		logOut.addActionListener(this);
		addProduct.addActionListener(this);
		addNewProduct.addActionListener(this);

		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(500, 200));

		add(logIn);
		add(logOut);
		add(addProduct);
		add(addNewProduct);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == logIn) {
			System.out.println("Logowanie");
		} else if (source == logOut) {
			System.out.println("Wylogowanie");
		} else if (source == addProduct) {
			try {
				String result = JOptionPane.showInputDialog(null, "Ilosc sztuk:");
				int count = Integer.parseInt(result);
				clientRef.getNetConn().addProduct(1, count);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}else if(source == addNewProduct){
			String name = JOptionPane.showInputDialog(null, "Podaj nazwe:");
			String manufacturer = JOptionPane.showInputDialog(null,"Podaj producenta:");
			String cena = JOptionPane.showInputDialog(null,"Podaj cene:");
			String ilosc = JOptionPane.showInputDialog(null,"Podaj ilosc:");
			try {
				Product newProduct = new Product(name,manufacturer,Double.parseDouble(cena),Integer.parseInt(ilosc));
				clientRef.getNetConn().addNewProduct(newProduct);
			} catch (NumberFormatException e1) {
				ShopImp.showMessage("Podano zle wartosci");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

}
