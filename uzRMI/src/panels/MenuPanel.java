package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
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
	private JButton searchProduct;
	private Client clientRef;
	private ItemList itemList;

	public MenuPanel(Client clientRef, JPanel itemList) {
		this.clientRef = clientRef;
		this.itemList = (ItemList)itemList;

		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(500, 100));

		logIn = new JButton("Log in");
		logOut = new JButton("Log out");
		addProduct = new JButton("Add product");
		addNewProduct = new JButton("Add new product");
		searchProduct = new JButton("Search");

		logIn.addActionListener(this);
		logOut.addActionListener(this);
		addProduct.addActionListener(this);
		addNewProduct.addActionListener(this);
		searchProduct.addActionListener(this);

		add(logIn);
		add(logOut).setVisible(false);
		add(addProduct).setVisible(false);
		add(addNewProduct).setVisible(false);
		add(searchProduct);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == logIn) {
			clientRef.setStatus(Id.ADMIN);
			adminButtons(true);

		} else if (source == logOut) {
			clientRef.setStatus(Id.USER);
			adminButtons(false);

		} else if (source == addProduct) {
			try {
				String result = JOptionPane.showInputDialog(null, "Ilosc sztuk:");
				int count = Integer.parseInt(result);
				clientRef.getNetConn().addProduct(1, count);
				itemList.refreshList();
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
				System.out.println(clientRef.getNetConn().getProductList().size());
				itemList.refreshList();
			} catch (NumberFormatException e1) {
				ShopImp.showMessage("Podano zle wartosci.");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				ShopImp.showMessage("Prosze uzupelnic wszystkie pola.");
			}
		} else if (source == searchProduct) {
			try {
				JDialog.setDefaultLookAndFeelDecorated(true);
				Object[] selectionValues = { "Id", "Name", "Manufacturer", "Price", "Quantity" };
				String initialSelection = "Id";
				String filter = JOptionPane.showInputDialog(null, "Po czym wyszukiwac?", "Search",
						JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection).toString();
				String search = JOptionPane.showInputDialog(null, "Szukane:");
				if (search == null)
					throw new NullPointerException();
				clientRef.getNetConn().searchProduct(filter, search);
			} catch (NullPointerException e2) {
				ShopImp.showMessage("Prosze uzupelnic pole.");
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
		}
	}

	private void adminButtons(boolean condition) {
		logIn.setVisible(!condition);
		logOut.setVisible(condition);
		addProduct.setVisible(condition);
		addNewProduct.setVisible(condition);
	}

}
