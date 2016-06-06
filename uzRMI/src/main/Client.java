package main;

enum Id{
	USER, ADMIN
}

public class Client {
	
	private Id status = Id.USER;

	public static void main(String[] args) {

	}

	public Id getStatus() {
		return status;
	}

	public void setStatus(Id status) {
		this.status = status;
	}

}
