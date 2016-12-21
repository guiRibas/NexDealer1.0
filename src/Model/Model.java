package Model;

//CLASSE DESTINADA PARA MODELOS DOS VEÍCULOS

public class Model {
	
	public Model() {
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private String description;
	private Brand brand;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}
