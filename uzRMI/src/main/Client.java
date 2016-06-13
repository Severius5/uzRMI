package main;

import java.awt.EventQueue;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import data.Id;
import data.Product;
import logic.Shop;
import panels.MyFrame;

//java -Djava.security.policy=java.policy main.Client
public class Client {

	private Shop netConn;
	private Id status = Id.USER;
	private ArrayList<Product> cart = new ArrayList<>();

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try {
			Remote remoteObject = Naming.lookup("rmi://127.0.0.1:11111/Sklep");
			if (remoteObject == null) {
				throw new RemoteException("Brak Sklepu");
			}

			Client client = new Client();
			client.netConn = (Shop) remoteObject;
			
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new MyFrame(client);
				}
			});
			System.out.println("Connected");
		} catch (NotBoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public Id getStatus() {
		return status;
	}

	public void setStatus(Id status) {
		this.status = status;
	}

	public ArrayList<Product> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Product> cart) {
		this.cart = cart;
	}
	
	public Shop getNetConn() {
		return netConn;
	}

	public synchronized void addToCart(Product item) {
		cart.add(item);
	}

	public boolean removeFromCart(int id) {
		for (Product product : cart) {
			if (product.getId() == id) {
				cart.remove(product);
				return true;
			}
		}
		return false;
	}

}