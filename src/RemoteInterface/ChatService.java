package RemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import RemoteInterface.models.Group;
import RemoteInterface.models.User;

public interface ChatService extends Remote {
	
	boolean register(String userName, String cipherPassword) throws RemoteException;
	
    User login(String userName, String cipherPassword) throws RemoteException;
    
    List<User> listOnlineUsers() throws RemoteException;
    
    List<Group> listGroups() throws RemoteException;
    
    boolean requestEnteringGroup(String GroupName, String userName) throws RemoteException;
    
    boolean aproveEnteringGroup(String nomeGrupo, String requesterName, String adminName) throws RemoteException;
    
    void sendPrivateMessage(String sender, String recipient, String message) throws RemoteException;
    
    void sendGroupMessage(String sender, String group, String message) throws RemoteException;
    
    void sendFile(String sender, String recipient, String fileName, byte[] content) throws RemoteException;
    
    boolean banUser(String requesterName, String targetName) throws RemoteException;
    
    boolean exitOrTransferGroup(String groupName, String userName) throws RemoteException;
	
}
