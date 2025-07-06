package RemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {

	void receiveMessage(String sender, String message) throws RemoteException;
	
}
