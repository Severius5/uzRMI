package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import logic.ShopImp;

//java -Djava.security.policy=java.policy main.Server
public class Server {

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try {

			ShopImp shopImp = new ShopImp();

			Registry registry = LocateRegistry.createRegistry(11111);
			registry.rebind("Sklep", shopImp);
			System.out.println("Start serwer");
		} catch (RemoteException e) {

			e.printStackTrace();
		}
	}
}
