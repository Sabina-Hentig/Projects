package ListaOrdonata;

public class Zombie {
	 String nume;
	 Integer distanta;
	
	Zombie(String numeZombie, int distantaZombie){
		this.nume = numeZombie;
		this.distanta = distantaZombie;
	}

	public String toString(){ 
		  return this.nume+" "+this.distanta;  
	 }

}	