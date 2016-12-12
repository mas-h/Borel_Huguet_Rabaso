package batailleNavale;



public class Coordonnee {
	private int ligne;
	private int colonne;
	//*******************************************************************************//
	//
	//								Constructeurs
	//
	//*******************************************************************************//
	
	public Coordonnee(int ligne, int colonne) { // constructeur 1: permet d'obtenir une coordonnee de ligne i et de colonne j
		this.ligne=ligne;
		this.colonne=colonne;
	}
public Coordonnee(String s) { // constructeur 2: permet d'obtenir une coordonnee à partir d'une string
		  
       char colonne1 = s.charAt(0); // numero de colonne = le premier caract de s
       int ligne1 = Integer.parseInt(s.substring(1)); // le numero de ligne = le reste de s
        
         
        //Si le nombre ne correspond pas à la taille de la grille
        if(ligne1<=0 || ligne1>25){throw new IllegalArgumentException();}  
        if(colonne1<'A' || colonne1>'Z'){throw new IllegalArgumentException();}
        
        this.ligne = ligne1;
        this.colonne=1+colonne1-'A'; // valeur numerique du caract 
}
	//*******************************************************************************//
	//
	//								Methodes
	//
	//*******************************************************************************//
	
public String toString() { // affiche la coordonnee
	
	char t = (char) (this.colonne+'A'-1);
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
		 //on verifie que l'objet est bien une coordonnee sinon on renvoie faux 
        if (! (obj instanceof Coordonnee))
        	return false;
        else{
        	Coordonnee m = (Coordonnee) obj; // transtypage de l'objet en Coordonnee
        	if (this.ligne==m.ligne && this.colonne==m.colonne){return true;} // si c'est les mêmes on renvoie vrai 
        	else{return false;} // sinon faux
        }
	}
	public boolean voisine(Coordonnee c) { 
		if(c.ligne==this.ligne-1 || c.ligne==this.ligne+1 || c.colonne==this.colonne-1 || c.colonne==this.colonne+1){ // 4 cas possibles, les 4 cases autour de this
			return true;
		}
		return false;
		
	}
	public int compareTo (Coordonnee c){ // retourne un entier négatif, 0 ou un entier positif en fonction de la compariason de this et c
	
			if(this.ligne<c.ligne ||this.ligne>c.ligne ){ // le cas où les lignes sont différentes 
				return (this.ligne-c.ligne);
			}
			else{return (this.colonne-c.colonne);} // le cas où les où les lignes sont égales, compare donc les colonnes entre elles 
			
	}
	public static void main(String[] args) {
        
		Coordonnee res= new Coordonnee(2,1);
		Coordonnee res2= new Coordonnee("B5");
		System.out.print(">" + res.toString() +"<");
		//System.out.print(">" + res.getLigne() +"<");
//		System.out.print(">" + res.getColonne() +"<");
//		System.out.print(">" + res.equals(res2) +"<");
//		System.out.print(">" + res.voisine(res2) +"<");
//	System.out.print(">" + res.compareTo(res2) +"<"); 
	}
}