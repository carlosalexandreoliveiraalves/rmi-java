package RemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	
	String sayHello() throws RemoteException;  
	//tem que lançar uma exceção própria do RMI
	//Métodos abstratos não possuem corpo
	// esse método tem que ser implmentado na classe que chama a interface
}
