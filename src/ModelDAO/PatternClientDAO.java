package ModelDAO;

public interface PatternClientDAO<T> {

	int create(T object, int id);
	
	int update(T object);
	
	int delete(Integer id);
	
}
