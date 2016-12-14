package batailleNavale;

public class GrilleNavale {

	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	// --------------Constructeurs------------------------//

	public GrilleNavale(int taille, int[] taillesNavires) {
		//On verifie si le tableau Tailles Navires n'est pas vide
		if(taillesNavires.length==0)throw new IllegalArgumentException();
		
		//on verifie si la taille de la grille est positive
		if(taille<=1)throw new IllegalArgumentException();
		
		this.taille = taille;
		this.nbNavires = 0;
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[(taille * taille)];
		this.navires = new Navire[taillesNavires.length];
		placementAuto(taillesNavires);
	}

	// permet d'obtenir une grille navale vide de taille taille pouvant accueillir jusqu'à nbNavires

	public GrilleNavale(int taille, int nbNavires) {
		

		if(nbNavires<=0)throw new IllegalArgumentException();
		if(taille<=0)throw new IllegalArgumentException();
		this.taille = taille;
		this.navires = new Navire[nbNavires];
		
		this.nbNavires = 0;
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[(taille * taille)];


	}

	// ------------------Méthodes-----------------------------------------------//

	public String toString() {
		
		
		//Ecriture de la première ligne avec les lettres de l'alphabeth
		String cases = "\t";
		for (int i = 0; i < this.taille; i++) {
			char c = (char) (i + 'A');
			cases = cases + c + "\t";
		}

		for (int i = 1; i < this.taille + 1; i++) {
			//ecriture de la première ligne avec les nombres
			cases = cases + "\n" + i;
			for (int j = 1; j < this.taille + 1; j++) {
				boolean bool = false;
				Coordonnee coord = new Coordonnee(i, j);
				//on regarde si la case correspond à une case ayant pris  un obus
				for (int k = 0; k < nbTirsRecus; k++) {
					if (tirsRecus[k].equals(coord)) {
						//si un bateau touché se trouve sur la case on marque X
						if (estTouche(coord)) {
							cases = cases + "\t X";
							bool = true;
							//si le tir est à l'eau on marque o
						} else if (estALEau(coord)) {
							cases = cases + "\t o";
							bool = true;
						}
					}
				}
				for (int k = 0; k < this.nbNavires; k++)
					//on parcours les bateaux si la case n'a pas déja été remplie par une tir X ou o
					if (navires[k].contient(coord) && bool == false) {
						//si elle contient un bateau
						cases = cases + "\t #";
						bool = true;
					}
				//Si  il n'y a ni tirs, ni bateaux dans la case alors mettre un point
				if (bool == false) {
					cases = cases + "\t .";
				}
			}
		}
		return (cases);
	}

	public boolean ajouteNavire(Navire n) {
		
		//On verifie que le bateau n est bien dans la grille
		if(!estDansGrille(n.getDebut())	&& !estDansGrille(n.getFin()))throw new IllegalArgumentException();
		//On verifie que le nombre maximum de bateau n'est pas atteint
		if(nbNavires==navires.length){System.out.print("Vous avez déja le nombre maximum de bateau");throw new IllegalArgumentException();}
		
		//Si c'est le premier navire on le rentre dans le tableau
		if(this.nbNavires==0){
			this.navires[0]=n;
			this.nbNavires += 1;
			return true;
		}
		
		// Si le bateau touche ou chevauche un bateau existant on relance la génération
		for (int i = 0; i < this.nbNavires; i++)
			if (this.navires[i].chevauche(n) || this.navires[i].touche(n)) {
				return false;}

		// si le bateau est valide on incremente nbNavires
		this.navires[nbNavires]=n;
		this.nbNavires += 1;
		return true;

	}

	public void placementAuto(int[] taillesNavires) {

		for (int i = 0; i < taillesNavires.length; i++) {
			//On gènère des coordonnées aléatoire valide avec la taille de la grille
			Coordonnee coord = new Coordonnee((int) (Math.random() * (taille - taillesNavires[i]))+1,(int) (Math.random() * (taille - taillesNavires[i])+1));
			//on gènère le boolean qui indiquera si le bateau est vertical ou horizontal
			boolean bool = Math.random() < 0.5;
			
			//Tant que le bateau n'a pas été accepté (qu'il ne chevauche, ni ne touche un autre bateau) on continue de génèrer des coordonnées
			while(!ajouteNavire( new Navire(coord, taillesNavires[i], bool))){
				coord = new Coordonnee((int) (Math.random() * (taille - taillesNavires[i])),(int) (Math.random() * (taille - taillesNavires[i])));
				bool = Math.random() < 0.5;
			}
		}
	}
	
	//on verifie que les coordonées en paramètres rentre dans la grille
	private boolean estDansGrille(Coordonnee c) {
		if (c.getLigne() <= taille && c.getColonne() <= taille && c.getLigne() > 0 && c.getColonne() > 0)
			return true;
		return false;
	}
	//On verifie si la coordonnées à été touchée par un obus
	private boolean estDansTirsRecus(Coordonnee c) {
		for (int i = 0; i < nbTirsRecus; i++)
			if (tirsRecus[i].equals(c))
				return true;
		return false;
	}
	//on ajoute les coordonnées c au tableau des Tirs recus et on increment le compteur
	private boolean ajouteDansTirsRecus(Coordonnee c) {
	
		tirsRecus[nbTirsRecus] = c;
		nbTirsRecus += 1;
		return false;

	}

	// Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si c ne
	// correspondait pas déjà à un tir reçu et a permis de toucher un navire de  this.
	public boolean recoitTir(Coordonnee c) {
		//On verifie si un tir n'a pas déja été effectué a ces coordonnées
		if(estDansTirsRecus(c))throw new IllegalArgumentException();
		// si il n'a pas encore était touché en c
		if (!estTouche(c)) {
			//on update le tableau partiesTouchees du navire
			for (int i = 0; i < this.nbNavires; i++){
				this.navires[i].recoitTir(c);
			}
			
			ajouteDansTirsRecus(c);
			return true;
		}
		return false;
	}

	// Retourne true si et seulement si un des navires présents dans this a été touché en c.
	public boolean estTouche(Coordonnee c) {
		//on parcours tous les navires pour voir si l'un d'entre eux a été touché en c
		for (int i = 0; i < this.nbNavires; i++){
			if (this.navires[i].estTouche(c))
				return true;
		}
		return false;
	}

	// Retourne true si et seulement si c correspond à un tir reçu dans l'eau par this.
	public boolean estALEau(Coordonnee c) {
		if (!estTouche(c))
			return true;
		return false;
	}

	// Retourne true si et seulement si un des navires présents dans this a été touché en c et est coulé
	public boolean estCoule(Coordonnee c) {
		if (estTouche(c)) {
			for (int j = 0; j < nbNavires; j++)
				if (navires[j].estCoule())
					return true;
		}
		return false;
	}

	public boolean perdu() {
		for (int j = 0; j < nbNavires; j++)
			if (!navires[j].estCoule())
				return false;
		return true;
	}

	public static void main(String[] args) {

		int[] tailleNav = { 2, 4 };
		Coordonnee[] tirs = { new Coordonnee("B2"), new Coordonnee("A1"), new Coordonnee("K3") };
//		GrilleNavale test = new GrilleNavale(10,tailleNav);
		GrilleNavale test = new GrilleNavale(10,3);
		test.recoitTir(new Coordonnee("A2"));


		Navire PetitNavire= new Navire(new Coordonnee("C3"), 4, false);
		Navire PetitNavire2= new Navire(new Coordonnee("I2"), 2, true);
		Navire PetitNavire3= new Navire(new Coordonnee("E7"), 5, true);

		test.ajouteNavire(PetitNavire);
		test.ajouteNavire(PetitNavire2);
		test.ajouteNavire(PetitNavire3);
		
		test.recoitTir(new Coordonnee("C3"));
		test.recoitTir(new Coordonnee("D3"));
		test.recoitTir(new Coordonnee("E3"));
		test.recoitTir(new Coordonnee("F3"));
		
		System.out.print("Le bateau est coulé :"+test.estCoule(new Coordonnee("C3"))+"\n");
	}
}