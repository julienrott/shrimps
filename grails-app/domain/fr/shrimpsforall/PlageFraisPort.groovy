package fr.shrimpsforall

class PlageFraisPort implements Serializable {

	long debut
	long fin
	double prix

	static belongsTo = [fraisPort: FraisPort]

    static constraints = {
    }
}
