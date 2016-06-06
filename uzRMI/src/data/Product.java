package data;

public class Product {

	private int quantity;
	private double price;
	private String name;
	private String manufacturer;

	public Product(String name, String manufacturer, double price, int quantity) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void addQuantity(int count) {
		this.quantity += count;
	}

	public void removeQuantity(int count) {
		this.quantity -= count;
	}

}
