package Model;

//CLASSE DESTINADA PARA VERSÕES DOS VEÍCULOS

public class Version {

	public Version() {
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private String description;
	private Model model;
	
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
