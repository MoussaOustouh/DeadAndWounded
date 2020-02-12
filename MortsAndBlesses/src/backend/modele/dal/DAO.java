package backend.modele.dal;
import java.util.ArrayList;

public interface DAO <T>{
	public void insert(T o);
	public void update(T o, String where);
	public void delete(String Where);
	public T find(String wehre, String order);
	public ArrayList<T> findAll(String wehre, String order);
}