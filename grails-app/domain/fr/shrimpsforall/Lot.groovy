package fr.shrimpsforall

class Lot {

	String titre
	double prix

	static belongsTo = [produit: Produit]
	
    static constraints = {
    }
}
