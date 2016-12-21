package Controller;

public interface PatternClientController<T> {

	public int insert(T object, int id);
	public int alter(T Object);
	public int remove(int id);
	
}
