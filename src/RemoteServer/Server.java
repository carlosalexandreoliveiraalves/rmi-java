package RemoteServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RemoteInterface.HelloImpl;

public class Server {
	
	public static void main(String[] args) {
		
		
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/Hello", new HelloImpl());
			// implementação do objeto remoto no Registry usando Naming
			// Naming cria o skeleton
			System.out.println("Servidor aberto na porta 1099");
			
		
		} catch (RemoteException | MalformedURLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
