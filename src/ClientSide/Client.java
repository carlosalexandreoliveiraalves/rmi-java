package ClientSide;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import RemoteInterface.ChatService;
import RemoteInterface.Hello;
import RemoteInterface.models.User;
import RemoteServer.ClientCallbackImpl;

//procurar o objeto remoto no Registry e devolver o stub
public class Client {

	public static void main(String[] args) {
		
		try {
			ChatService chat = (ChatService) Naming.lookup("rmi://localhost:1099/chat"); //para onde etá 'olhando'
			
			
			Scanner sc = new Scanner(System.in);
            System.out.println("1 - Registrar");
            System.out.println("2 - Login");
            int op = sc.nextInt(); sc.nextLine();

            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Senha (criptografada): ");
            String senha = sc.nextLine();

            if (op == 1) {
                boolean success = chat.register(nome, senha);
                System.out.println(success ? "Registrado com sucesso!" : "Usuário já existe.");
            } else {
            	User user = chat.login(nome, senha);
            	if (user != null) {
            	    System.out.println("Bem-vindo, " + user.getName());

            	    // Registra o callback para receber mensagens
            	    ClientCallbackImpl callback = new ClientCallbackImpl();
            	    chat.registerCallback(user.getName(), callback);

            	    // Loop de envio de mensagens
            	    while (true) {
            	        System.out.print("\n> Enviar para: ");
            	        String destinatario = sc.nextLine();
            	        System.out.print("> Mensagem: ");
            	        String msg = sc.nextLine();
            	        chat.sendPrivateMessage(user.getName(), destinatario, msg);
            	    }

            	} else {
            	    System.out.println("Login inválido.");
            	}
            }
			
			// System.out.println(stub.sayHello());
			
			
			
			
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
