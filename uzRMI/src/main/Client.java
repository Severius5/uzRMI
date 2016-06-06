package main;

import java.awt.EventQueue;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import data.Product;
import logic.Shop;
import panels.MyFrame;

enum Id{
	USER, ADMIN
}

public class Client {
	
	protected static Shop netConn;
	private Id status = Id.USER;
	private ArrayList<Product> cart = new ArrayList<>();

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try {
			Remote remoteObject = Naming.lookup("rmi://127.0.0.1:11111/Sklep");
			if (remoteObject == null) {
				throw new RemoteException("Brak Sklepu");
			}
			netConn = (Shop) remoteObject;
			
			EventQueue.invokeLater(new Runnable(){

				@Override
				public void run() {
					new MyFrame();
				}
				
			});
					
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

}
