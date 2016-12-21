package Model;

import ModelDAO.StateDAO;

public class State {

	private int id;
	private String name;
	private String initials;
	private static StateDAO stateDao = new StateDAO();
	
	public State(String name, String initials) {
		this.name = name;
		this.initials = initials;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}

	public static State findById(int id){
		return stateDao.findById(id);
	}
	
}
