package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	
	
	
//	public boolean caPasse (Navire c){
//        if (debut.getColonne() == fin.getColonne()){ // Si le bateau est vertical 
//        	// on regarde si les les coordonnees de fin ne depasse pas de la grille 
//            if ((fin.getLigne())>25) return false;
//            else  return true;
//        }
//        else  if (fin.getColonne()>'Z') return false;
//        else  return true;
//
//    }    

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		
		

		//On verifie que debut est une coordonnées valide
		 if (! (debut instanceof Coordonnee))throw new IllegalArgumentException();
		if(longueur<0 ||longueur>25){throw new IllegalArgumentException();}
		
		//On verifie si il y a la place de mettre un bateau à cet endroit
//		if (caPasse(this)== false){throw new IllegalArgumentException();}
		
		this.debut=debut;
		if (estVertical) { this.fin= new Coordonnee(debut.getColonne(),(debut.getLigne()+longueur-1)); }
		else{this.fin= new Coordonnee((debut.getColonne()+longueur-1),debut.getLigne());}

		nbTouchees=0;
		partiesTouchees = new Coordonnee [longueur];
		
//		System.out.print(">" + this.debut.toString() +"  "+debut.getColonne()+"  "+debut.getLigne()+"   "+ longueur+"<\n");
//		System.out.print(">" + this.fin.toString() +"  "+fin.getColonne()+"  "+fin.getLigne()+"   "+ longueur+"<\n \n");
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
		for(int i=0;i<partiesTouchees.length;i++){
			if(partiesTouchees[i].equals(c)){return true;}
		}
		return false;
	}
	public boolean estTouche() {	
		if(partiesTouchees[0]!=null)return true;
		return false;
	}
	public boolean estCoule() {
		if(partiesTouchees[partiesTouchees.length-1]!=null)return false;
		return true;
	}
	
	
	public static void main(String[] args) {
		Coordonnee coord= new Coordonnee("B6");
		Coordonnee coord2= new Coordonnee("B4");
		Navire PetitNavire= new Navire(coord, 4, true);
		Navire PetitNavire2= new Navire(coord2, 4, true);
		Navire PetitNavire3= new Navire(coord2, 5, true);
//		System.out.print(">" + PetitNavire.toString() +"<\n");
//		System.out.print(">" + PetitNavire2.toString() +"<\n");
//		System.out.print(">" + PetitNavire3.toString() +"<\n");
//		Coordonnee res2= new Coordonnee("C4");
//		System.out.print(">" + PetitNavire.contient(tir) +"<");
//		System.out.print(">" + PetitNavire.contient(PetitNavire2) +"<");
		System.out.print(">" + PetitNavire.chevauche(PetitNavire2) +"<");
	}
}
