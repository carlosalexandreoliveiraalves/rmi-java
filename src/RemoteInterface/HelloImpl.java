package RemoteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

	public HelloImpl() throws RemoteException {
		super(); //chmando da classe pai
		// TODO Auto-generated constructor stub
	} // tem que gerar um construtor

	private static final long serialVersionUID = 1L; 
	
	@Override
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello world";
	} 
	//implementação do método da interface 
	// esse será o "objeto remoto" pois implementa a interface remota
	// e é necessário herdar de "UnicastRemoteObject"
}
