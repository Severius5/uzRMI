package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.Product;

public interface Shop extends Remote{
	
	public String searchProduct(String firtr, String search) throws RemoteException;

	public Product buyProduct(int productId, int count) throws RemoteException;

	public void showProducts() throws RemoteException;  // nie wiem czy potrzebne

	public void addNewProduct(Product newProduct) throws RemoteException;

	public void addProduct(int productId, int count) throws RemoteException;

}
