package RemoteServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import RemoteInterface.ChatService;
import RemoteInterface.ClientCallback;
import RemoteInterface.models.Group;
import RemoteInterface.models.User;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

	 private final Map<String, String> usersPassword;
	 private final Map<String, User> onlineUsers;
	 private final Map<String, Group> groups;
	 private final Map<String, ClientCallback> userCallbacks = new ConcurrentHashMap<>();
	
	protected ChatServiceImpl() throws RemoteException {
		super();
		usersPassword = new ConcurrentHashMap<>();
	    onlineUsers = new ConcurrentHashMap<>();
	    groups = new ConcurrentHashMap<>();
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public boolean register(String userName, String cipherPassword) throws RemoteException {
		if (usersPassword.containsKey(userName)) {
			return false;
		};
		usersPassword.put(userName, cipherPassword);
		return true;
	}

	@Override
	public User login(String userName, String cipherPassword) throws RemoteException {
		if (cipherPassword.equals(usersPassword.get(userName))) {
            User user = new User(userName, true);
            onlineUsers.put(userName, user);
            return user;
        }
        return null;
	}

	@Override
	public List<User> listOnlineUsers() throws RemoteException {
		 return new ArrayList<>(onlineUsers.values());
	}

	@Override
	public List<Group> listGroups() throws RemoteException {
		return new ArrayList<>(groups.values());
	}

	@Override
	public boolean requestEnteringGroup(String groupName, String userName) throws RemoteException {
		Group group = groups.get(groupName);
        if (group != null && !group.getParticipants().contains(userName)) {
            // é aprovado automaticamente
            group.getParticipants().add(userName);
            return true;
        }
        return false;
	}

	@Override
	public boolean aproveEnteringGroup(String groupName, String requesterName, String adminName) {
		Group group = groups.get(groupName);
	    if (group != null && group.getAdmin().equals(adminName)) {
	        group.getParticipants().add(requesterName);
	        return true;
	    }
	    return false;
	}

	@Override
	public void sendPrivateMessage(String sender, String recipient, String message) throws RemoteException {
		 System.out.printf("[Privado] %s -> %s: %s%n", sender, recipient, message);
		 ClientCallback callback = userCallbacks.get(recipient);
		 
		 if (callback != null) {
			 callback.receiveMessage(sender, message);
		 } else {
		     System.out.printf("⚠️ Usuário %s não possui callback registrado.\n", recipient);
		 }
	
	}

	@Override
	public void sendGroupMessage(String sender, String group, String message) throws RemoteException {
		System.out.printf("[Grupo %s] %s: %s%n", group, sender, message);
	}

	@Override
	public void sendFile(String sender, String recipient, String fileName, byte[] content) throws RemoteException {
		System.out.printf("%s enviou o arquivo '%s' para %s (%d bytes)%n", sender, fileName, recipient, content.length);
	}

	@Override
	public boolean banUser(String requesterName, String targetName) throws RemoteException {
		if (onlineUsers.containsKey(targetName)) {
            onlineUsers.remove(targetName);
            return true;
        }
        return false;
	}

	@Override
	public boolean exitOrTransferGroup(String groupName, String userName) throws RemoteException {
		Group group = groups.get(groupName);
        if (group != null) {
            group.getParticipants().remove(userName);
            if (group.getAdmin().equals(userName)) {
                List<String> participants = group.getParticipants();
                if (participants.isEmpty()) {
                    groups.remove(groupName);
                } else {
                    String newAdmin = participants.get(0);
                    groups.put(groupName, new Group(groupName, newAdmin, participants));
                }
            }
            return true;
        }
        return false;
	}

	@Override
	public void registerCallback(String userName, ClientCallback callback) throws RemoteException {
		userCallbacks.put(userName, callback);
		
	}

	@Override
	public void unregisterCallback(String userName) throws RemoteException {
		userCallbacks.remove(userName);
		
	} 
	
	
}
