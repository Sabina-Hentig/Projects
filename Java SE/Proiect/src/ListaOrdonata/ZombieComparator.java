package ListaOrdonata;

import java.util.Comparator;

public class ZombieComparator implements Comparator<Zombie>{

	@Override
	public int compare(Zombie o1, Zombie o2) {
		
		return o1.distanta.compareTo(o2.distanta);
	}
	
}
