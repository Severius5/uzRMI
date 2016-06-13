package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
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
	private DefaultListModel<String> model;
	private Client clientRef;
	private CartList cartList;

	public ItemList(Client clientRef, JPanel cartList) {
		setLayout(new BorderLayout());
		model = new DefaultListModel<>();
		list = new JList<>(model);
		JScrollPane pane = new JScrollPane(list);
		pane.setPreferredSize(new Dimension(250, 150));
		JButton addProductBtn = new JButton("Add product");
		JButton buyProductBtn = new JButton("Buy product");
		this.clientRef = clientRef;
		this.cartList = (CartList) cartList;
		try {
			this.productListRef = clientRef.getNetConn().getProductList();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		showList();

		addProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (clientRef.getStatus() == Id.ADMIN) {
						int id = Integer.parseInt(getIdFromList());
						int count = Integer.parseInt(JOptionPane.showInputDialog(null, "Ilosc sztuk:"));
						clientRef.getNetConn().addProduct(id, count);
						refreshList();
						cartList.repaint();
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
					int productId = Integer.parseInt(getIdFromList());
					int count = Integer.parseInt(JOptionPane.showInputDialog(null, "Podaj ilosc:"));
					if (count == 0) {
						ShopImp.showMessage("Prosze kupic wiecej niz 0 sztuk.");
						return;
					}
					Product tmp = clientRef.getNetConn().buyProduct(productId, count);
					if (tmp != null) {
						clientRef.addToCart(tmp);
						refreshList();
					}
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
			e.printStackTrace();
		}
		model.removeAllElements();
		for (Product product2 : productListRef) {
			model.addElement(product2.toString());
		}
		cartList.refreshList();
	}

	public void showList() {
		for (Product product : productListRef) {
			model.addElement(product.toString());
		}
	}

	private String getIdFromList() {
		String prod = list.getSelectedValue();
		String first = prod.substring(0, 1);
		String second = prod.substring(1, 2);
		if (!second.equals(".")) {
			return first + second;
		} else {
			return first;
		}
	}

	public void showSearchList(List<Product> searchList) {
		model.removeAllElements();
		for (Product product : searchList) {
			model.addElement(product.toString());
		}
	}

}