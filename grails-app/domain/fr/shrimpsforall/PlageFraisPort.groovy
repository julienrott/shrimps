package fr.shrimpsforall

class PlageFraisPort {

	long debut
	long fin
	double prix

	static belongsTo = [fraisPort: FraisPort]

    static constraints = {
    }
}
