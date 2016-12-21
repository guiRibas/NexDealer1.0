package ModelDAO;

public interface PatternVehicleDAO<T> {

	int create(T object, int idVersion, int idType);
	
	int update(T object);
	
	int delete(Integer id);
	
}
