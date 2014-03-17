package fr.shrimpsforall

class LigneCommande implements Serializable {

	Produit produit
	String titre
	int quantite
	double prix
	Lot lot

	static belongsTo = [commande: Commande]

    static constraints = {
    	lot(nullable: true)
    }
}
