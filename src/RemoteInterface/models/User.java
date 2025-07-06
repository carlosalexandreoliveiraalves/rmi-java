package RemoteInterface.models;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private boolean online;
	
	
	public User(String name, boolean online) {
		this.name = name;
		this.online = online;
	};
	
	public String getName() {
		return name;
	};
	
	public void setName(String nome) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
