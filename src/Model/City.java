package Model;

import ModelDAO.CityDAO;

public class City {
	
	private int id;
	private String name;
	private State state;
	private static CityDAO cityDao = new CityDAO();
	
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public static City findById(int id){
		return cityDao.findById(id);
	}
	
	public int findByName(String name){
		return cityDao.findByName(name);
	}
}
