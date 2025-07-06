package RemoteServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import RemoteInterface.ClientCallback;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {

	public ClientCallbackImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
	}

	private static final long serialVersionUID = 1L;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	@Override
	public void receiveMessage(String sender, String message) throws RemoteException {
		System.out.printf("\n Message from %s: %s", sender, message);
		
	}

}
