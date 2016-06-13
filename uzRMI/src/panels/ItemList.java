package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Id;
import data.Product;
import logic.ShopImp;
import main.Client;

public class ItemList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private List<Product> productListRef;
	private int amountOfItems;
	private DefaultListModel<String> model;
	private Client clientRef;

	public ItemList(Client clientRef) {
		setLayout(new BorderLayout());
		// setPreferredSize(new Dimension(500, 100));
		model = new DefaultListModel<>();
		list = new JList<>(model);
		JScrollPane pane = new JScrollPane(list);
		JButton addProductBtn = new JButton("Add product");
		JButton buyProductBtn = new JButton("Buy product");
		this.clientRef = clientRef;
		try {
			this.productListRef = clientRef.getNetConn().getProductList();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.amountOfItems = productListRef.size();
		showList();
		// for (int i = 0; i < 15; i++)
		// model.addElement("Element " + i);

		addProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (clientRef.getStatus() == Id.ADMIN) {
						String productId = list.getSelectedValue();
						int id = Integer.parseInt(productId.substring(0, 1));
						String result = JOptionPane.showInputDialog(null, "Ilosc sztuk:");
						int count = Integer.parseInt(result);
						clientRef.getNetConn().addProduct(id, count);
						refreshList();
					} else {
						ShopImp.showMessage("Wymagane prawa administratora");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					ShopImp.showMessage("Zly format");
				}

			}
		});
		buyProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String prod = list.getSelectedValue();
					int productId = Integer.parseInt(prod.substring(0, 1));
					int count = Integer.parseInt(JOptionPane.showInputDialog(null, "Podaj ilosc:"));
					clientRef.addToCart(clientRef.getNetConn().buyProduct(productId, count));
					refreshList();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					ShopImp.showMessage("Podano zle wartosci.");
				}
			}
		});

		add(pane, BorderLayout.NORTH);
		add(addProductBtn, BorderLayout.WEST);
		add(buyProductBtn, BorderLayout.EAST);
	}

	public void refreshList() {
		try {
			this.productListRef = clientRef.getNetConn().getProductList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.removeAllElements();
		for (Product product2 : productListRef) {
			model.addElement(product2.toString());
		}
	}

	public void showList() {
		for (Product product : productListRef) {
			model.addElement(product.toString());
		}
	}

}
