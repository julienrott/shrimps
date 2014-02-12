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
}