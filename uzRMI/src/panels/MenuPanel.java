package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.Id;
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

		add(logIn).setVisible(false);
		add(logOut).setVisible(false);
		add(addProduct).setVisible(false);
		add(addNewProduct).setVisible(false);
		
		if(clientRef.getStatus() == Id.ADMIN){
			logOut.setVisible(true);
			addProduct.setVisible(true);
			addNewProduct.setVisible(true);
		} else {
			logIn.setVisible(true);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == logIn) {
			clientRef.setStatus(Id.ADMIN);
		} else if (source == logOut) {
			clientRef.setStatus(Id.USER);
		} else if (source == addProduct) {
			try {
				String result = JOptionPane.showInputDialog(null, "Ilosc sztuk:");
				int count = Integer.parseInt(result);
				clientRef.getNetConn().addProduct(1, count);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				ShopImp.showMessage("Zly format");
			}
		} else if (source == addNewProduct) {
			try {
				String name = JOptionPane.showInputDialog(null, "Podaj nazwe:");
				String manufacturer = JOptionPane.showInputDialog(null, "Podaj producenta:");
				double cena = Double.parseDouble(JOptionPane.showInputDialog(null, "Podaj cene:"));
				int ilosc = Integer.parseInt(JOptionPane.showInputDialog(null, "Podaj ilosc:"));
				if (name == null || manufacturer == null)
					throw new NullPointerException();
				clientRef.getNetConn().addNewProduct(new Product(name, manufacturer, cena, ilosc));
			} catch (NumberFormatException e1) {
				ShopImp.showMessage("Podano zle wartosci");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				ShopImp.showMessage("Prosze uzupelnic wszystkie pola");
			}
		}
	}

}
