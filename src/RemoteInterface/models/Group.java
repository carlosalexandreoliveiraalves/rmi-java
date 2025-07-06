package RemoteInterface.models;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String admin;
	private List<String> participants;
	
	public Group(String name, String admin, List<String> participants) {
		this.name = name;
		this.admin = admin;
		this.participants = participants;
	}
	
	public String getName() {
        return name;
    }

    public String getAdmin() {
        return admin;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
	
}
