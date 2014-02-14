package fr.shrimpsforall

class Panier {
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
			totalProduits += it.quantite * it.produit.prix
		}
		totalProduits
	}

	def totalFraisPort() {
		def totalFraisPort = 0
		lignes.each {
			totalFraisPort += it.quantite * it.produit.fraisPort
		}
		totalFraisPort
	}

	def totalTTC() {
		def totalTTC = 0
		lignes.each {
			totalTTC += it.quantite * (it.produit.prix + it.produit.fraisPort)
		}
		totalTTC
	}
}