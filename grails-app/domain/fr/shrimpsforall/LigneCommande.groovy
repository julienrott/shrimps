package fr.shrimpsforall

class LigneCommande implements Serializable {

	Produit produit
	String titre
	int quantite
	double prix
	double fraisPort

	static belongsTo = [commande: Commande]

    static constraints = {
    }
}
