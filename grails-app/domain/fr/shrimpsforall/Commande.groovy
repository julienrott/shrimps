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
	String paypalTransactionId
	String stripeChargeId

	static belongsTo = [client: User]

	static hasMany = [lignes: LigneCommande]

    static constraints = {
    	dateCreated(nullable: true)
    	statut(inList: ["création", "paiement en validation", "payée", "expédiée"])
    	nom(nullable: true)
    	prenom(nullable: true)
    	adresse(nullable: true)
    	complAdresse(nullable: true)
    	codePostal(nullable: true)
    	ville(nullable: true)
    	paypalTransactionId(nullable: true)
    	stripeChargeId(nullable: true)
    }

    
	def totalProduits() {
		def totalProduits = 0
		lignes.each {
			//totalProduits += it.lot ? it.lot.prix * it.quantite : it.produit.prix * it.quantite
			totalProduits += it.quantite * (it.lot ? it.lot.prix : it.produit.prix)
		}
		totalProduits.round(2)
	}


    def totaux() {
		lignes.each{
			it.produit = Produit.get(it.produit.id)
		}

		def totalProduits = totalProduits()
		def totalTTC = totalProduits + fraisPort

		[totalProduits: totalProduits, totalTTC: totalTTC.round(2)]
	}
}
