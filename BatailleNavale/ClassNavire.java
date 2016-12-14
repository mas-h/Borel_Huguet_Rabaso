package batailleNavale;

import batailleNavale.Coordonnee;
import batailleNavale.Navire;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	
		/*									//
		 	* 									//
		 	* 			Constructeurs			//
		 	*	 								//
		 	* 									//
		*/									//

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
	
		//On verifie que debut est une coordonnées valide
		 if (! (debut instanceof Coordonnee))throw new IllegalArgumentException();
		 if(longueur<0 ||longueur>25){throw new IllegalArgumentException();} // On controle la longueur du bateau 
		
		
		// on regarde si les les coordonnees de fin ne depasse pas de la grille, s'il y a la place pour créer le navire.
		if (estVertical) if ((debut.getLigne()+longueur-1)>25) throw new IllegalArgumentException(); // Si le bateau est vertical 
	    else  if (debut.getColonne()+longueur-1>'Z')throw new IllegalArgumentException(); // Si le bateau est horizontal
 
		  
		this.debut=debut;
		if (estVertical) { this.fin = new Coordonnee((debut.getLigne()+longueur-1),debut.getColonne()); } // on affecte les coordonées de fin du bateau en fonction de son inclinaison
		else{this.fin = new Coordonnee(debut.getLigne(),(debut.getColonne()+longueur-1));}

		nbTouchees=0;
		partiesTouchees = new Coordonnee [longueur]; // affecte un tableau de coordonnées de la longueur du navire
		
	}
	
		/*									//
 			* 									//
 			* 			Methodes				//
 			*	 								//
 			* 									//
 		*/									//

	
	public String toString(){ // affiche this avec ces coordonées de debut et de fin, sa longueur ainsi que son inclinaison
		String str ="Navire ("; 
		String inclinaison ="";
		int lg;
		if(this.fin.getLigne() != this.debut.getLigne()){ // Si le bateau est vertical
			inclinaison ="Vertical";
			lg =(this.fin.getLigne()-this.debut.getLigne()+1);
			}
		else{inclinaison = "Horizontal";
			lg =(this.fin.getColonne()-this.debut.getColonne()+1);
		}
		str =str+this.debut+","+fin+","+lg+","+inclinaison+")";
		return(str);
		
	}
	
	public Coordonnee getDebut() {	return this.debut;} // retourne les coordonnées de debut du navire
	public Coordonnee getFin()   {	return this.fin;  } // retourne les coordonnées de fin du navire
	
	public boolean contient(Coordonnee c) { // retourne vrai si le navire contient c
		
		//si le bateau est vertical
		if(this.fin.getLigne() != this.debut.getLigne()){
			for(int i=this.debut.getLigne(); i <= this.fin.getLigne(); i++){
				Coordonnee bateau = new Coordonnee(i,debut.getColonne()); // Creation d'une nouvelle coordonnée pour parcourir le bateau
				if(c.equals(bateau)){return true;} // Si on retrouve une équivalence alors le bateau contient c
			}
		}
		//si il est horizontal
		else{
			for(int i=this.debut.getColonne() ; i <= this.fin.getColonne(); i++){
				Coordonnee bateau = new Coordonnee(debut.getLigne(),i); // même chose pour parcourir un bateau horizontal
				if(c.equals(bateau)){return true;}
			}
		}
		return false;
	}

	public boolean touche(Navire n) { // retourne vrai si this est adjacent à un autre navire c
//				a
//				#
//			  c ##### d	(this)
//				#
//				#
//				b
//			   (n)
//si chevauche sur les lignes
		if((n.getFin().getLigne() >= this.getDebut().getLigne() && this.getFin().getLigne() >= n.getDebut().getLigne())){ // Si b(ligne) >= c(ligne) ET d(ligne) >= a(ligne)
			if ((n.getDebut().getColonne() == this.getDebut().getColonne()+1) || (n.getDebut().getColonne() == this.getDebut().getColonne()-1) // Si a(colonne) = c(colonne +1) OU a(colonne = c(colonne-1)
					||(n.getDebut().getColonne()==this.getFin().getColonne()+1) || (n.getDebut().getColonne() == this.getFin().getColonne()-1)) // OU a(colonne) = d (colonne +1) OU a(colonne) = d(colonne-1)
				return true; // alors vrai

			else if((n.getFin().getColonne() == this.getDebut().getColonne()+1) || (n.getFin().getColonne() == this.getDebut().getColonne()-1)// Sinon Si b(colonne) = c (colonne +1) OU b(colonne) = c(colonne-1)
					|| (n.getFin().getColonne() == this.getFin().getColonne()+1) || (n.getFin().getColonne() == this.getFin().getColonne()-1))// OU b(colonne) = d(colonne+1) OU b(colonne) = d(colonne-1)
				return true; // alors vrai
			return false; // Si aucune de ces conditions alors faux
		}
		
		//si chevauche sur les colonnes
		else if ((n.getFin().getColonne() >= this.getDebut().getColonne() && this.getFin().getColonne() >= n.getDebut().getColonne())){ //Sinon Si b(colonne) >= c(colonne) ET d(colonne) >= a(colonne)
			if ((n.getDebut().getLigne() == this.getDebut().getLigne()+1) || (n.getDebut().getLigne() == this.getDebut().getLigne()-1) //	Si a(ligne) = c(ligne+1) OU a(ligne) = c(ligne -1)
					|| (n.getDebut().getLigne() == this.getFin().getLigne()+1) || (n.getDebut().getLigne() == this.getFin().getLigne()-1)) // OU a(ligne) = d(ligne +1) OU a(ligne) = d (ligne-1)
				return true; // alors on renvoie vrai 
			
			else if ((n.getFin().getLigne() == this.getDebut().getLigne()+1) || (n.getFin().getLigne() == this.getDebut().getLigne()-1) // Sinon Si b(ligne) = c(ligne+1) OU b(ligne) = c (ligne-1) 
					|| (n.getFin().getLigne() == this.getFin().getLigne()+1) || (n.getFin().getLigne() == this.getFin().getLigne()-1)) // OU b (ligne) = d(ligne+1) OU b(ligne) = d(ligne-1)
				return true; // alors vrai
			return false; //Si aucune de ces conditions alors faux
		}
		return false; // Si on est dans aucuns de TOUS ces cas on renvoie faux
	} // on ferme la methode
	
	public boolean chevauche(Navire n) { // retourne true si this et se chevauchent
		//				a
		//				#
		//			c ##### d	this
		//				#
		//				#
		//				b
		// On regarde si this et n sont dans les mêmes intervalles de lignes et de colonnes en fonction des ligne et colonnes des coordonées de debut et fin des navires 
		if((n.getFin().getLigne() >= this.getDebut().getLigne() && this.getFin().getLigne() >= n.getDebut().getLigne()) 
				&& (n.getFin().getColonne() >= this.getDebut().getColonne() && this.getFin().getColonne() >= n.getDebut().getColonne())){
			return true; // si c'est le cas on renvoie true
		}
		
		return false;
		
	}

	
	public boolean recoitTir(Coordonnee c) { // retourne true ssi this contient c 
		if(this.contient(c)){
			partiesTouchees[nbTouchees]= c; // si c'est le cas, on ajoute la coordonée c dans le tableau parties touchées
			nbTouchees+=1; // on incrémente de 1 le nombre de parties touchées du bateau 
			return true;
		}
		return false;
	}
	public boolean estTouche(Coordonnee c) { // retourne true ssi this a été touché à la coordonnée c 
		for(int i=0; i<nbTouchees; i++){ // on parcours le tableau partiesTouchées en fonction du nombre de fois où this a été touché
			if(c.equals(partiesTouchees[i])){return true;} // si une coordonnée du tableau correspond à c on renvoie true
		}
		return false;
	}
	public boolean estTouche() {	// retourne true, si this a été touché au moins au fois
		if(partiesTouchees[0] != null)return true; // on vérifie la première case du tableau, si elle n'est pas vide alors on renvoie true
		return false;
	}
	public boolean estCoule() { // retourne true si toutes les coordonnées du navire ont été touchées
		if(partiesTouchees[partiesTouchees.length-1]==null)return false; // si la dernière case du tableau est vide alors on renvoie false
		return true;
		// if nbtouchee == longueur // deuxième option
	}
	
	
	public static void main(String[] args) {
		Coordonnee coord = new Coordonnee("B2");
		Coordonnee coord2 = new Coordonnee("C6");
		Coordonnee tir = new Coordonnee("B3");
		Navire PetitNavire = new Navire(coord, 4, true);
//		PetitNavire.recoitTir(tir);
		Navire PetitNavire2 = new Navire(coord2, 4, false);
//		Navire PetitNavire3= new Navire(coord2, 5, true);
		System.out.print(">" + PetitNavire.toString() +"<\n");
		System.out.print(">" + PetitNavire2.toString() +"<\n");
//		System.out.print(">" + PetitNavire3.toString() +"<\n");
//		Coordonnee res2= new Coordonnee("C4");
//		System.out.print(">" + PetitNavire.contient(tir) +"<");
//		System.out.print(">" + PetitNavire.contient(PetitNavire2) +"<");
//		System.out.print(">" + PetitNavire.chevauche(PetitNavire2) +"<");
		System.out.print(">" + PetitNavire.touche(PetitNavire2) +"<");
//		System.out.print(">" + PetitNavire.recoitTir(tir) +"<");
//		System.out.print(">" + PetitNavire.estTouche(tir) +"<");
//		System.out.print(">" + PetitNavire.estTouche() +"<");
	}
}