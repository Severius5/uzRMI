package main;

import java.util.ArrayList;

import data.Product;

enum Id{
	USER, ADMIN
}

public class Client {
	
	private Id status = Id.USER;
	private ArrayList<Product> cart = new ArrayList<>();

	public static void main(String[] args) {

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
