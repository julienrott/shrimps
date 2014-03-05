package fr.shrimpsforall

class FraisPort {

	String titre
	String type

	static hasMany = [plages: PlageFraisPort]

    static constraints = {
    	type(inList: ["fixe", "poids"])
    }
}
