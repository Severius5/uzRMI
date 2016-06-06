package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import data.Product;

public class ShopImp  extends UnicastRemoteObject implements Shop{
	
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Product> productList = new HashMap<>();
	
	public ShopImp() throws RemoteException{
		productList.put(1, new Product("Kubek","China",12.5,6));
		productList.put(2, new Product("LG G4", "Asia", 2500, 2));
		productList.put(3, new Product("Talerz", "Poland", 4.99, 10));
	}

	@Override
	public String searchProduct(String filter, String search) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product buyProduct(int productId, int count) throws RemoteException {
		if(productList.containsKey(productId) && productList.get(productId).getQuantity()>=count){
			productList.get(productId).removeQuantity(count);
			Product bought = productList.getOrDefault(productId, null);
			bought.setQuantity(count);
			return bought;
		}else{
			//popup message o braku produktu
			return null;
		}
	}

	@Override
	public void showProducts() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewProduct(Product newProduct) throws RemoteException {
		//if(newProduct.getName().equals(productList.containsValue(newProduct.getName()))){
			productList.put(productList.size()+1, newProduct);
			//popup message o dodaniu
		//}
	}

	@Override
	public void addProduct(int productId, int count) throws RemoteException {
		if(productList.containsKey(productId)){
			productList.get(productId).addQuantity(count);
			//popup message	o dodaniu
		}else{
			//popup message o braku produktu
		}
	}
	

}
