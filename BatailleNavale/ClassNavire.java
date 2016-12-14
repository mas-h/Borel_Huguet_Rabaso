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
		if (estVertical) { this.fin= new Coordonnee((debut.getLigne()+longueur-1),debut.getColonne()); }
		else{this.fin= new Coordonnee(debut.getLigne(),(debut.getColonne()+longueur-1));}

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
		str=str+this.debut+","+fin+","+lg+","+inclinaison+")";
		return(str);
		
	}
	
	public Coordonnee getDebut() {	return this.debut;}
	public Coordonnee getFin() {	return this.fin;	}
	
	public boolean contient(Coordonnee c) {
		
		//si le bateau est vertical
		if(this.fin.getLigne()!=this.debut.getLigne()){
			for(int i=this.debut.getLigne();i<=this.fin.getLigne();i++){
				Coordonnee bateau= new Coordonnee(i,debut.getColonne());
				if(c.equals(bateau)){return true;}
			}
		}
		//si il est horizontal
		else{
			for(int i=this.debut.getColonne();i<=this.fin.getColonne();i++){
				Coordonnee bateau= new Coordonnee(debut.getLigne(),i);
				if(c.equals(bateau)){return true;}
			}
		}
		return false;
	}

	public boolean touche(Navire n) {
		
		
		if(!(this.getFin().getLigne()<n.getDebut().getLigne()||n.getFin().getColonne()<this.getDebut().getColonne())){
			
		}
		
//		if (this.getDebut().voisine(n.getDebut()) || this.getDebut().voisine(n.getFin()))
//			return true;
//			
//		if (this.getFin().voisine(n.getDebut()) || this.getFin().voisine(n.getFin()))
//			return true;
		
		return false;

		
	}
	public boolean chevauche(Navire n) {
		//				a
		//				#
		//			c ##### d	this
		//				#
		//				#
		//				b
		//
		//		!(b	<	c	||	d	<	a)
		//		&&!(b	<	c)	&& !(d	<	a)
		//		&&  b	>=	c	&& 	d	>=	a
		
		if((n.getFin().getLigne()>=this.getDebut().getLigne() &&this.getFin().getLigne()>=n.getDebut().getLigne())
				&&(n.getFin().getColonne()>=this.getDebut().getColonne() &&this.getFin().getColonne()>=n.getDebut().getColonne())){
			return true;
		}
		
		//a		n.getDebut().getColonne()
		//b		n.getFin().getColonne()
		//c		this.getDebut().getLigne()
		//d		this.getFin().getLigne()
		
//		if(!(n.getFin().getColonne()<this.getDebut().getLigne() 	||	this.getFin().getLigne()<n.getDebut().getColonne())
//				&&(!( n.getFin().getColonne()<this.getDebut().getLigne())	&&	!(this.getFin().getLigne()<n.getDebut().getColonne()))	
//				&&(n.getFin().getColonne()>=this.getDebut().getLigne()	&&	this.getFin().getLigne()>=n.getDebut().getColonne()))
						
		
		return false;
		
		
//		//si le This est vertical
//				if(this.fin.getLigne()!=this.debut.getLigne()){
//					for(int i=this.debut.getLigne();i<this.fin.getLigne();i++){
//						Coordonnee bateau= new Coordonnee(debut.getColonne(),i);	
//						//On verifie si N est horizontal ou pas
//						if(n.fin.getLigne()!=n.debut.getLigne()){if(n.contient(bateau)){return true;}}
//						else{if(n.contient(bateau)){return true;}}}
//				}
//
//				else{
//					
//					for(int i=this.debut.getColonne();i<this.fin.getColonne();i++){
//						Coordonnee bateau= new Coordonnee(i,debut.getLigne());
//						//On verifie si N est horizontal ou pas
//						if(n.fin.getLigne()!=n.debut.getLigne()){if(n.contient(bateau)){return true;}}
//						else{if(n.contient(bateau)){return true;}}
//					}
//				}
//				return false;
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
		if(partiesTouchees[partiesTouchees.length-1]!=null)return false;
		return true;
	}
	
	
	public static void main(String[] args) {
		Coordonnee coord= new Coordonnee("B2");
		Coordonnee coord2= new Coordonnee("B6");
		Coordonnee tir= new Coordonnee("B3");
		Navire PetitNavire= new Navire(coord, 4, true);
//		PetitNavire.recoitTir(tir);
		Navire PetitNavire2= new Navire(coord2, 4, false);
//		Navire PetitNavire3= new Navire(coord2, 5, true);
		System.out.print(">" + PetitNavire.toString() +"<\n");
		System.out.print(">" + PetitNavire2.toString() +"<\n");
//		System.out.print(">" + PetitNavire3.toString() +"<\n");
//		Coordonnee res2= new Coordonnee("C4");
//		System.out.print(">" + PetitNavire.contient(tir) +"<");
//		System.out.print(">" + PetitNavire.contient(PetitNavire2) +"<");
		System.out.print(">" + PetitNavire.chevauche(PetitNavire2) +"<");
//		System.out.print(">" + PetitNavire.touche(PetitNavire2) +"<");
//		System.out.print(">" + PetitNavire.recoitTir(tir) +"<");
//		System.out.print(">" + PetitNavire.estTouche(tir) +"<");
//		System.out.print(">" + PetitNavire.estTouche() +"<");
	}
}