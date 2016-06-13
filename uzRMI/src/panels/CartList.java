package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Product;
import logic.ShopImp;
import main.Client;

public class CartList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private List<Product> cartRef;
	private DefaultListModel<String> model;
	private Client clientRef;

	public CartList(Client clientRef) {
		setLayout(new BorderLayout());
		model = new DefaultListModel<>();
		list = new JList<>(model);
		JScrollPane pane = new JScrollPane(list);
		pane.setPreferredSize(new Dimension(250,150));
		JButton deleteProduct = new JButton("Delete");
		this.clientRef = clientRef;
		this.cartRef = clientRef.getCart();
		refreshList();

		deleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int productId;
					String prod = list.getSelectedValue();
					String first = prod.substring(0, 1);
					String second = prod.substring(1, 2);
					if (!second.equals(".")) {
						productId = Integer.parseInt(first + second);
					} else {
						productId = Integer.parseInt(first);
					}
					if (!clientRef.removeFromCart(productId)) {
						ShopImp.showMessage("Brak produktu.");
					}
					refreshList();
				} catch (NumberFormatException e1) {
					ShopImp.showMessage("Podano zle wartosci.");
				} catch (NullPointerException e1){
				}
			}
		});

		add(pane, BorderLayout.NORTH);
		add(deleteProduct, BorderLayout.EAST);
	}

	public void refreshList() {
		this.cartRef = clientRef.getCart();
		model.removeAllElements();
		for (Product product : cartRef) {
			model.addElement(product.toString());
		}
	}

}