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
	public List<Product> searchProduct(String filter, String search) throws RemoteException {
		String parametr = filter.toLowerCase();
		List<Product> searchList = new ArrayList<>();
		
		switch (parametr) {
		
		case "id":
			int id = Integer.parseInt(search);
			for(Product product:productList){
				if(product.getId() == id){
					searchList.add(product);
				}
			}
			return searchList;
			
		case "name":
			for(Product product:productList){
				if(product.getName().contains(search)){
					searchList.add(product);
				}
			}
			return searchList;
			
		case "manufacturer":
			for(Product product:productList){
				if(product.getManufacturer().contains(search)){
					searchList.add(product);
				}
			}
			return searchList;
			
		case "price":
			double price = Double.parseDouble(search);
			for(Product product:productList){
				if(product.getPrice() == price){
					searchList.add(product);
				}
			}
			return searchList;
			
		case "quantity":
			int quantity = Integer.parseInt(search);
			for(Product product:productList){
				if(product.getQuantity() == quantity){
					searchList.add(product);
				}
			}
			return searchList;

		}
		return null;
	}

	@Override
	public Product buyProduct(int productId, int count) throws RemoteException {
		for (Product product : productList) {
			if (product.getId() == productId && product.getQuantity() >= count) {
				product.removeQuantity(count);
				Product bought = new Product(product);
				bought.setQuantity(count);
				return bought;
			}
		}
		showMessage("Brak danego produktu");
		return null;
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
				return;
			} 
		}
		showMessage("Brak danego produktu na liscie.");
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public List<Product> getProductList() {
		return productList;
	}
	

}
