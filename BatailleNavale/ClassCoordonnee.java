package batailleNavale;

public class Coordonnee {

	private int ligne;
	private int colonne;
	//*******************************************************************************//
	//
	//								Constructeurs
	//
	//*******************************************************************************//
	
	public Coordonnee(int ligne, int colonne) {
		 if(ligne<0 || ligne>25 ||colonne<0 || colonne>25){throw new IllegalArgumentException();}  
		this.ligne=ligne;
		this.colonne=colonne;
	}
	public Coordonnee(String s) {

        char colonneLettre = s.charAt(0);
        int ligneEnCours = Integer.parseInt(s.substring(1));

        //Si le nombre ne correspond pas à la taille de la grille
        if(ligneEnCours<0 || ligneEnCours>25){throw new IllegalArgumentException();}  
        //Si le caractère n'est pas dans le tableau donc non valide
        if(colonneLettre<'A' || colonneLettre>'Z') {throw new IllegalArgumentException();}
        
        this.ligne=ligneEnCours;
		this.colonne=1+colonneLettre-'A';
	
	}
	public String toString() {
		char t=(char) (this.colonne+'A'-1);
		String s=""+t+this.ligne;
		return(s);
	}
	public int getLigne() {
		return this.ligne;
	}
	public int getColonne() {
		return this.colonne;
	}
	public boolean equals(Object obj) {
		 //on verifie que l'objet est bien une coordonnes sinon msg d'erreur
        if (! (obj instanceof Coordonnee))return false;
        else{
        	Coordonnee m = (Coordonnee) obj;
        	if (this.ligne==m.ligne && this.colonne==m.colonne){return true;}
        	else{return false;}
        }
	}
	public boolean voisine(Coordonnee c) {
//		if(c.ligne==this.ligne-1 || c.ligne==this.ligne+1 || c.colonne==this.colonne-1 || c.colonne==this.colonne+1){
		if((c.ligne==this.ligne-1 || c.ligne==this.ligne+1)&&c.colonne==this.colonne){
		return true;
		}
		else if ((c.colonne==this.colonne-1 || c.colonne==this.colonne+1)&&c.ligne==this.ligne){
			return true;
		}
		return false;
	}
	

	
	
	public int compareTo(Coordonnee c){
		if(this.ligne<c.ligne ||this.ligne>c.ligne ){
			return (this.ligne-c.ligne);
		}
		else{return (this.colonne-c.colonne);}
		
	}

	
	public static void main(String[] args) {
	        
		Coordonnee res= new Coordonnee("B5");
		Coordonnee res2= new Coordonnee("A5");
//		System.out.print(">" + res.toString() +"<");
//		System.out.print(">" + res.getLigne() +"<");
//		System.out.print(">" + res.getColonne() +"<");
//		System.out.print(">" + res.equals(res2) +"<");
		System.out.print(">" + res.voisine(res2) +"<");
//	System.out.print(">" + res2.compareTo(res) +"<");
	}
	
}