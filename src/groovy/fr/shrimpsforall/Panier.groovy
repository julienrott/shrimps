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
		totalProduits.round(2)
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

		if(hasFraisPortColis) {
			def fp = FraisPort.findByTitre("colis")
			def plage = fp.plages.grep{it.debut <= poidsTotal && poidsTotal <= it.fin}
			totalFraisPort += plage.prix
		}
		else if(hasFraisPortMiniMax) {
			def fp = FraisPort.findByTitre("mini max")
			def plage = fp.plages.grep{it.debut <= poidsTotal && poidsTotal <= it.fin}
			totalFraisPort += plage.prix
		}

		def totalProduits = totalProduits()
		def totalTTC = totalProduits + totalFraisPort

		[totalProduits: totalProduits, totalFraisPort: totalFraisPort, totalTTC: totalTTC, poidsTotal: poidsTotal]
	}
}