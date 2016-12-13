package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin; 
	private Coordonnee [] partiesTouchees;
	private int nbTouchees;
	
	//*******************************************************************************//
		//
		//								Constructeurs
		//
	//*******************************************************************************//
	
	
	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		
		//On verifie que debut est une coordonnées valide
		if (! (debut instanceof Coordonnee))throw new IllegalArgumentException();
		if(longueur<0 ||longueur>25){throw new IllegalArgumentException();}
		// On controle que le navire  soit créé avec une longueur d'au moins 2, permets aussi de vérifier qu'on ne puisse pas rentrer de valeurs négatives
		if (longueur<2){throw new IllegalArgumentException();}
		
		// on regarde si les les coordonnees de fin ne depasse pas de la grille 
		if (estVertical) if ((debut.getLigne()+longueur-1)>25) throw new IllegalArgumentException();
	    else  if (debut.getColonne()+longueur-1>'Z')throw new IllegalArgumentException();
 
		  
		this.debut=debut;
		if (estVertical) { this.fin= new Coordonnee(debut.getColonne(),(debut.getLigne()+longueur-1)); }
		else{this.fin= new Coordonnee((debut.getColonne()+longueur-1),debut.getLigne());}

		nbTouchees=0;
		partiesTouchees = new Coordonnee [longueur];
		
	}
		
//		System.out.print(">" + this.debut.toString() +"  "+debut.getColonne()+"  "+debut.getLigne()+"   "+ longueur+"<\n");
//		System.out.print(">" + this.fin.toString() +"  "+fin.getColonne()+"  "+fin.getLigne()+"   "+ longueur+"<\n \n");
	
	
//*******************************************************************************//
	//
	//								Methodes
	//
//*******************************************************************************//
	// petit boolean pratique à insérer dans toutes les fonctions pour que ça soit plus digeste !
	public boolean estVertical(Navire c){
		return (this.debut.getColonne()== this.fin.getColonne());
	}
	
	public String toString(){
		String str="Navire (";
		String inclinaison="";
		int lg;
		if(this.fin.getLigne()!=this.debut.getLigne()){
			inclinaison="Vertical";
			lg=(this.fin.getLigne()-this.debut.getLigne()+1);
			}
		else{inclinaison="Horizontal";
		lg=(this.fin.getColonne()-this.debut.getColonne()+1);
		}
		str=str+this.debut+","+this.fin+","+lg+","+inclinaison+")";
		return(str);
		
	}
	
	public Coordonnee getDebut() {	return this.debut;}
	public Coordonnee getFin() {	return this.fin;	}
	
	public boolean contient(Coordonnee c) { // retourne true ssi un bateau occupe la coordonnee c
		
		//si le bateau est vertical
		if(this.fin.getLigne()!=this.debut.getLigne()){
			for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){ // on parcourt la longueur du bateau en créant des nouvelles coordonnees 
				Coordonnee bateau= new Coordonnee(debut.getColonne(),i); // que l'on compare avec la coordonnee c 
				if(c.equals(bateau)){return true;} 
			}
		}
		//si il est horizontal
		else{
			for(int i=this.debut.getColonne();i<this.fin.getColonne();i++){
				Coordonnee bateau= new Coordonnee(i,debut.getLigne());
				if(c.equals(bateau)){return true;}
			}
		}
		return false;
	}

	public boolean touche(Navire n) { // renvoie true ssi this est adjacent à un autre bateau
		
		//si le This.bateau est vertical
		if(this.fin.getLigne()!=this.debut.getLigne()){
			
			for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){
				Coordonnee bateau= new Coordonnee(debut.getColonne(),i);
				
				//On verifie si N est horizontal ou pas
				if(n.fin.getLigne()!=n.debut.getLigne()){
					for(int j=n.debut.getLigne();j<n.fin.getLigne();j++){
						Coordonnee bateauN= new Coordonnee(n.debut.getColonne(),j); // on parcours n et on vérifie s'il est pas voisin a this 
						if(bateauN.voisine(bateau)){return true;}
					}
				}
				else{
					for(int j=n.debut.getColonne();j<n.fin.getColonne();j++){
						Coordonnee bateauN= new Coordonnee(j,n.debut.getLigne());
						if(bateauN.voisine(bateau)){return true;}
					}

				}
			}
		}
			//si il est horizontal
		else{
			
			for(int i=this.debut.getColonne();i<this.fin.getColonne();i++){
				Coordonnee bateau= new Coordonnee(i,debut.getLigne());
				
				//On verifie si N est horizontal ou pas
				if(n.fin.getLigne()!=n.debut.getLigne()){
					for(int j=n.debut.getLigne();j<n.fin.getLigne();j++){
						Coordonnee bateauN= new Coordonnee(n.debut.getColonne(),j);
						if(bateauN.voisine(bateau)){return true;}
					}
				}
				else{
					for(int j=n.debut.getColonne();j<n.fin.getColonne();j++){
						Coordonnee bateauN= new Coordonnee(j,n.debut.getLigne());
						if(bateauN.voisine(bateau)){return true;}
					}
				}
			}
		}
		return false;
		
	}
	public boolean chevauche(Navire n) { // retourne vrai ssi n chevauche this
		//si le This est vertical
				if(this.fin.getLigne()!=this.debut.getLigne()){
					
					for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){
						Coordonnee bateau= new Coordonnee(debut.getColonne(),i); // on parcours les coordonnees de this
						
						//On verifie si N est horizontal ou pas
						if(n.fin.getLigne()!=n.debut.getLigne()){if(n.contient(bateau)){return true;}} // on vérifie si n contient une coordonnee de this en fonction de son inclinaison
				
						else{if(n.contient(bateau)){return true;}}}
				}

				else{
					
					for(int i=this.debut.getColonne();i<this.fin.getColonne();i++){
						Coordonnee bateau= new Coordonnee(i,debut.getLigne());
						
						//On verifie si N est horizontal ou pas
						if(n.fin.getLigne()!=n.debut.getLigne()){if(n.contient(bateau)){return true;}}
						else{if(n.contient(bateau)){return true;}}
					}
				}
				return false;
	}
	public boolean recoitTir(Coordonnee c) { // 
		if(this.contient(c)){
			partiesTouchees[nbTouchees]=c;
			nbTouchees+=1;
			return true;
		}
		return false;
	}
	public boolean estTouche(Coordonnee c) {
		for(int i=0;i<nbTouchees;i++){ // //si au moins une coordonnes a ete touchée
			if(partiesTouchees[i].equals(c)){return true;}
			
		}
		return false;
	}
	public boolean estTouche() {
			 //si au moins une coordonnes a ete touchée
		return (nbTouchees!=0);
	}
	public boolean estCoule() {
		int longueur;
		if (this.debut.getColonne()== this.fin.getColonne()) { // dans le cas où le navire est verticale
			longueur= (this.fin.getLigne()-this.debut.getLigne())+1;
		}
		else {													// dans le cas où le navire est horizontale
			longueur = (this.fin.getColonne()-this.debut.getColonne())+1;
		}
		return (this.nbTouchees == longueur); //à voir si on fait une fonction qui donne la longueur et définis si verticale ou horizontale 
		
	}
	
	
	public static void main(String[] args) {
		Coordonnee coord= new Coordonnee("B2");
		Coordonnee coord2= new Coordonnee("B3");
		Coordonnee tir= new Coordonnee("B2");
		Navire PetitNavire= new Navire(coord, 2, true);
		Navire PetitNavire2= new Navire(coord2, 4, false);
//		Navire PetitNavire3= new Navire(coord2, 5, true);
		System.out.print(">" + PetitNavire.toString() +"<\n");
		System.out.print(">" + PetitNavire2.toString() +"<\n");
//		System.out.print(">" + PetitNavire3.toString() +"<\n");
//		Coordonnee res2= new Coordonnee("C4");
		System.out.print(">" + PetitNavire.contient(tir) +"<");
//		System.out.print(">" + PetitNavire.contient(PetitNavire2) +"<");
		System.out.print(">" + PetitNavire.chevauche(PetitNavire2) +"<");
		System.out.print(">" + PetitNavire.recoitTir(tir) +"<");
		System.out.print(">" + PetitNavire.estTouche(tir) +"<");
		System.out.print(">" + PetitNavire.estCoule() +"<");
	}
}

