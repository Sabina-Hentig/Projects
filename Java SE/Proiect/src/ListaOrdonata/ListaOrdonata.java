package ListaOrdonata;
import java.util.Iterator;


public interface ListaOrdonata<T> {

	public void adauga(T element);
	
	public boolean cauta(T element);
	
	public int numarulElementelor();
	
	public T stergePozitie(int pozitie);
	
	public void sterge(T element);
	
	public T element(int pozitie);
	
	public boolean vida();
	
	public int pozitie(T element);
	
	public Iterator<T> iterator();


	
}
