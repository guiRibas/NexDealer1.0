package Model;

public class Sale {
	
	private int id;
	private Client client;
	private Vehicle vehicle;
	private Type type;
	private String total;
	private String annotation;
	private String date;
	private String pdfWay;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Vehicle getVechicle() {
		return vehicle;
	}
	public void setVechicle(Vehicle vechicle) {
		this.vehicle = vechicle;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPdfWay() {
		return pdfWay;
	}
	public void setPdfWay(String pdfWay) {
		this.pdfWay = pdfWay;
	}
	
}
