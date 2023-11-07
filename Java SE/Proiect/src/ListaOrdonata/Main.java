package ListaOrdonata;

import java.util.Scanner;

public class Main {
	 ListaOrdonata<Zombie> distantaZombie = new ListaOrdonataImplementat<Zombie>(new ZombieComparator());
	
	public static int citIntreg(String prompter) {
		System.out.print(prompter);					
		try {										
			Scanner s=new Scanner(System.in);
			int I=s.nextInt();						
			return I;
		}
		catch(Exception e) {
			System.out.println("ai gresit optiunea");
			citIntreg(prompter);					
		}
		return 0;
	}

	public static long Meniu() {
		System.out.println("1.Afisare distante zombie");
		System.out.println("2.Survive or GameOver?");
		System.out.println("0.Terminare program");
		return citIntreg("alege o optiune: ");
	}
	

	
	public static void main(String[] args) {
		ListaOrdonata<Zombie> distantaZombie = new ListaOrdonataImplementat<Zombie>(new ZombieComparator());
			distantaZombie.adauga(new Zombie( "Zombie1" , 3));
			distantaZombie.adauga(new Zombie( "Zombie2" , 4));
			distantaZombie.adauga(new Zombie( "Zombie3" , 6));
			distantaZombie.adauga(new Zombie( "Zombie4" , 2));
			distantaZombie.adauga(new Zombie( "Zombie5" , 5));  
		
		boolean gameOver = false;
		int atac = 0;
		int ratat = 3;

		long Op= Meniu();{
		   while(Op!=0){
			   switch((int)Op) {
			   		case 1: {
			   			for (int i=0; i<distantaZombie.numarulElementelor(); i++) {
			   					System.out.println(distantaZombie.element(i).nume+" "+distantaZombie.element(i).distanta);
			   				}
			   				
			   				}		        						        	    
			           	    break;
			        case 2: {
			        	while(distantaZombie.vida() == false && gameOver==false) {
			        		for (int i=0;i<distantaZombie.numarulElementelor();i++){
			        			atac++;
			        			System.out.println(" ");
			        			System.out.println("Atacuri: "+ atac);
			        			if( atac != ratat || atac % ratat != 0) {
			        				distantaZombie.stergePozitie(0);
			        				System.out.println("Nr. zombie: "+ distantaZombie.numarulElementelor());
			        			}					
			        			for (int j=0;j<distantaZombie.numarulElementelor();j++){
					        		System.out.println("Distante: "+ (distantaZombie.element(j).distanta-atac));
					        		if((distantaZombie.element(j).distanta-atac)==0) {
					        			gameOver=true;
					        			System.out.println("DEAD! GAME OVER! ");
			        					break;
					        		}
			        			}						   
			        							
			        		}					
			        	}
			        	if(distantaZombie.vida()==true && gameOver==false) {
			        			System.out.println("SURVIVED! CONGRATULATIONS! ");
			        	}	
			        }
			        
			        	break;
			        
				    default:
				        	System.out.println("Ai gresit optiunea, mai incearca");
				   }
				   Op=Meniu();
			   }
			   System.out.println("Program terminat"); 

		}
}
}
