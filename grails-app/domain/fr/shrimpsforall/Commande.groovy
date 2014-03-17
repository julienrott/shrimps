package fr.shrimpsforall

class Commande implements Serializable {

	Date dateCreated
	String statut
	String nom
	String prenom
	String adresse
	String complAdresse
	String codePostal
	String ville
	double fraisPort

	static belongsTo = [client: User]

	static hasMany = [lignes: LigneCommande]

    static constraints = {
    	dateCreated(nullable: true)
    	statut(inList: ["payée", "expédiée"])
    	nom(nullable: true)
    	prenom(nullable: true)
    	adresse(nullable: true)
    	complAdresse(nullable: true)
    	codePostal(nullable: true)
    	ville(nullable: true)
    }
}
