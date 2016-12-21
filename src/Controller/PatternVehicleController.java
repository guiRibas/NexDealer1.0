package Controller;

public interface PatternVehicleController<T> {

	public int insert(T Object, int idVersion, int idType);
	public int alter(T Object);
	public int remove(int id);
	
}
