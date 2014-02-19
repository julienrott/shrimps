package fr.shrimpsforall

class Adresse implements Serializable {

	String ligne1
	String ligne2
	String codePostal
	String ville

	static belongsTo = [client: User]

    static constraints = {
    	ligne2 nullable: true
    }
}
