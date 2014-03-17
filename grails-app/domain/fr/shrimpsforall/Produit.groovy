package fr.shrimpsforall

class Produit implements Serializable {

	String titre
	String description
	double prix
	Categorie categorie
	FraisPort fraisPort
	long poids
	int stock = -1

	static hasMany = [photos: Photo, lots: Lot]

    static constraints = {
    	fraisPort(nullable: true)
    }

    static mapping = {
		description(type: "text")
	}
}
