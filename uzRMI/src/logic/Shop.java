package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.Product;

public interface Shop extends Remote{
	
	public List<Product> searchProduct(String filter, String search) throws RemoteException;

	public Product buyProduct(int productId, int count) throws RemoteException;

	public void addNewProduct(Product newProduct) throws RemoteException;

	public void addProduct(int productId, int count) throws RemoteException;
	
	public List<Product> getProductList() throws RemoteException;

}
