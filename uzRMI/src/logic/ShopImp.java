package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Product;

public class ShopImp extends UnicastRemoteObject implements Shop {

	private static final long serialVersionUID = 1L;
	private List<Product> productList = new ArrayList<>();

	public ShopImp() throws RemoteException {
		productList.add(new Product("Kubek", "China", 12.5, 6));
		productList.add(new Product("LG G4", "Asia", 2500, 2));
		productList.add(new Product("Talerz", "Poland", 4.99, 10));
	}

	@Override
	public String searchProduct(String filter, String search) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product buyProduct(int productId, int count) throws RemoteException {
		for (Product product : productList) {
			if (product.getId() == productId && product.getQuantity() >= count) {
				product.removeQuantity(count);
				Product bought = product;
				bought.setQuantity(count);
				return bought;
			}
		}
		showMessage("Brak danego produktu");
		return null;
	}

	@Override
	public void showProducts() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewProduct(Product newProduct) throws RemoteException {
		for (Product product : productList) {
			if (product.getName().equals(newProduct.getName())) {
				showMessage("Produkt o takiej nazwie juz istnieje.");
				return;
			}
		}
		productList.add(newProduct);
		showMessage("Dodano nowy produkt");
	}

	@Override
	public void addProduct(int productId, int count) throws RemoteException {
		for (Product product : productList) {
			if (product.getId() == productId) {
				product.addQuantity(count);
				showMessage("Dodano produkt.");
			} else {
				showMessage("Brak danego produktu na liscie.");
			}
		}
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
