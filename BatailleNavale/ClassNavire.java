package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
	
		//On verifie que debut est une coordonnées valide
		 if (! (debut instanceof Coordonnee))throw new IllegalArgumentException();
		if(longueur<0 ||longueur>25){throw new IllegalArgumentException();}
		
		
		// on regarde si les les coordonnees de fin ne depasse pas de la grille 
		if (estVertical) if ((debut.getLigne()+longueur-1)>25) throw new IllegalArgumentException();
	    else  if (debut.getColonne()+longueur-1>'Z')throw new IllegalArgumentException();
 
		  
		this.debut=debut;
		if (estVertical) { this.fin= new Coordonnee(debut.getColonne(),(debut.getLigne()+longueur-1)); }
		else{this.fin= new Coordonnee((debut.getColonne()+longueur-1),debut.getLigne());}

		nbTouchees=0;
		partiesTouchees = new Coordonnee [longueur];
		
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
	
	public boolean contient(Coordonnee c) {
		
		//si le bateau est vertical
		if(this.fin.getLigne()!=this.debut.getLigne()){
			for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){
				Coordonnee bateau= new Coordonnee(debut.getColonne(),i);
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

	public boolean touche(Navire n) {
		
		//si le This.bateau est vertical
		if(this.fin.getLigne()!=this.debut.getLigne()){
			
			for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){
				Coordonnee bateau= new Coordonnee(debut.getColonne(),i);
				
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
	public boolean chevauche(Navire n) {
		//si le This est vertical
				if(this.fin.getLigne()!=this.debut.getLigne()){
					
					for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){
						Coordonnee bateau= new Coordonnee(debut.getColonne(),i);
						
						//On verifie si N est horizontal ou pas
						if(n.fin.getLigne()!=n.debut.getLigne()){if(n.contient(bateau)){return true;}}
				
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
	
	
	public boolean recoitTir(Coordonnee c) {
		if(this.contient(c)){
			partiesTouchees[nbTouchees]=c;
			nbTouchees+=1;
			return true;
		}
		return false;
	}
	public boolean estTouche(Coordonnee c) {
		for(int i=0;i<nbTouchees;i++){
			if(c.equals(partiesTouchees[i])){return true;}
		}
		return false;
	}
	public boolean estTouche() {	
		if(partiesTouchees[0]!=null)return true;
		return false;
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
//		Coordonnee tir= new Coordonnee("H3");
		Navire PetitNavire= new Navire(coord, 4, true);
		Navire PetitNavire2= new Navire(coord2, 4, false);
//		Navire PetitNavire3= new Navire(coord2, 5, true);
		System.out.print(">" + PetitNavire.toString() +"<\n");
		System.out.print(">" + PetitNavire2.toString() +"<\n");
//		System.out.print(">" + PetitNavire3.toString() +"<\n");
//		Coordonnee res2= new Coordonnee("C4");
//		System.out.print(">" + PetitNavire.contient(tir) +"<");
//		System.out.print(">" + PetitNavire.contient(PetitNavire2) +"<");
//		System.out.print(">" + PetitNavire.chevauche(PetitNavire2) +"<");
//		System.out.print(">" + PetitNavire.recoitTir(tir) +"<");
//		System.out.print(">" + PetitNavire.estTouche(tir) +"<");
//		System.out.print(">" + PetitNavire.estTouche() +"<");
	}
}