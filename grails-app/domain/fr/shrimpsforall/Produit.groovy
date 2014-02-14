package fr.shrimpsforall

class Produit {

	String titre
	String description
	double prix
	double fraisPort
	Categorie categorie

	static hasMany = [photos: Photo]

    static constraints = {
    }

    static mapping = {
		description(type: "text")
	}
}
