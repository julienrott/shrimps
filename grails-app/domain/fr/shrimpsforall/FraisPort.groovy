package fr.shrimpsforall

class FraisPort implements Serializable {

	String titre
	String type

	static hasMany = [plages: PlageFraisPort]

    static constraints = {
    	type(inList: ["fixe", "poids"])
    }
}
