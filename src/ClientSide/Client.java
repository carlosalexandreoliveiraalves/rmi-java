package ClientSide;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RemoteInterface.Hello;

//procurar o objeto remoto no Registry e devolver o stub
public class Client {

	public static void main(String[] args) {
		
		try {
			Hello stub = (Hello) Naming.lookup("rmi://localhost:1099/Hello");
			System.out.println(stub.sayHello());
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		//list e lookup no lado do cliente
		// lookup devolve um objeto remoto
		
		
	}

}
