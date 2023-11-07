package ListaOrdonata;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;



public class ListaOrdonataImplementat<T> implements ListaOrdonata<T> {

	private Comparator<T> comparator;
	private List<T> elemente;
	
	public ListaOrdonataImplementat(Comparator<T> comparator) {
		this.comparator = comparator;
		this.elemente = new ArrayList<T>();
	}
	
	public void adauga(T element) {
		if (this.elemente.size() == 0) {
			this.elemente.add(element);
		} else if (this.comparator.compare(element, this.elemente.get(this.elemente.size()-1))>= 0) {
			this.elemente.add(element);
		} else {
			int index = 0;
			while(index < this.elemente.size() && this.comparator.compare(element, this.elemente.get(index)) > 0) {
				index++;
			}
			this.elemente.add(index, element);
		}
	}

	public boolean cauta(T element) {
		return this.elemente.contains(element);
	}

	public int numarulElementelor() {
		return this.elemente.size();		
	}

	public T stergePozitie(int pozitie) {
		if (pozitie < 0 || pozitie >= this.elemente.size()) {
			throw new RuntimeException("Pozitie invalida!");
		}
		T element = this.elemente.remove(pozitie);
		return element;
	}

	public void sterge(T element) {
		this.elemente.remove(element);
		
	}

	public T element(int pozitie) {
		if (pozitie <0 || pozitie >= this.elemente.size()) {
			throw new RuntimeException("Pozitie invalida!");
		}
		return this.elemente.get(pozitie);
	}

	public boolean vida() {
		return this.elemente.isEmpty();
	}

	public int pozitie(T element) {
		return this.elemente.indexOf(element);
	}

	public Iterator<T> iterator() {
		return new IteratorListaOrdonata<T>(this);
	}
	
	public class IteratorListaOrdonata<T> implements Iterator<T> {

		private ListaOrdonataImplementat<T> lista;
		private int pozitieCurenta;
		
		public IteratorListaOrdonata(ListaOrdonataImplementat<T> list) {
			this.lista = list;
			this.pozitieCurenta = 0;			
		}
		
		public boolean hasNext() {
			return this.pozitieCurenta < this.lista.numarulElementelor();			
		}

		public T next() {
			T element = this.lista.element(this.pozitieCurenta);
			this.pozitieCurenta++;
			return element;			
		}
		
	}


}
