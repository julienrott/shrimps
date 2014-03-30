package fr.shrimpsforall

class Panier implements Serializable {
	def lignes

	def quantiteProduits() {
		def quantiteProduits = 0
		lignes.each {
			quantiteProduits += it.quantite
		}
		quantiteProduits
	}

	def totalProduits() {
		def totalProduits = 0
		lignes.each {
			//totalProduits += it.lot ? it.lot.prix * it.quantite : it.produit.prix * it.quantite
			totalProduits += it.quantite * (it.lot ? it.lot.prix : it.produit.prix)
		}
		totalProduits > 0 ? totalProduits.round(2) : 0
	}

	def totaux() {
		lignes.each{
			it.produit = Produit.get(it.produit.id)
		}

		def totalFraisPort = 0

		def hasFraisPortFixe = false
		def produitsWithFraisPortFixe = lignes.grep{it.produit.fraisPort.type == "fixe"}

		if(produitsWithFraisPortFixe.size() > 0) {
			hasFraisPortFixe = true
			totalFraisPort = produitsWithFraisPortFixe[0].produit.fraisPort.plages[0].prix
		}

		def hasFraisPortColis = false
		def produitsWithFraisPortColis = lignes.grep{it.produit.fraisPort.type == "poids" && it.produit.fraisPort.titre == "colis"}

		if(produitsWithFraisPortColis.size() > 0) {
			hasFraisPortColis = true
		}

		def hasFraisPortMiniMax = false
		def produitsWithFraisPortMiniMax = lignes.grep{it.produit.fraisPort.type == "poids" && it.produit.fraisPort.titre == "mini max"}

		if(produitsWithFraisPortMiniMax.size() > 0) {
			hasFraisPortMiniMax = true
		}

		def poidsTotal = 0

		lignes.each {
			if (it.produit.fraisPort.type != "fixe") {
				poidsTotal += it.quantite * it.produit.poids
			}
		}

		def fp

		if(hasFraisPortColis) {
			fp = FraisPort.findByTitre("colis")
		}
		else if(hasFraisPortMiniMax) {
			fp = FraisPort.findByTitre("mini max")
		}

		def plages = PlageFraisPort.where{
			eq "fraisPort", fp
			lte "debut", poidsTotal
			gte "fin", poidsTotal
		}.list()
		
		totalFraisPort += plages.size() > 0 ? plages.first().prix : 0

		def totalProduits = totalProduits()
		def totalTTC = totalProduits + totalFraisPort

		[totalProduits: totalProduits, totalFraisPort: totalFraisPort, totalTTC: totalTTC, poidsTotal: poidsTotal]
	}
}