package fr.shrimpsforall

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(Panier)
@Build([Produit, FraisPort, PlageFraisPort])
class PanierSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test panier 2 produits fixe"() {
        given: 
            def panier = new Panier()

            def plage = PlageFraisPort.build(debut: 0, fin: 0, prix: 20)
            def fraisPort = FraisPort.build(type: "fixe", plages: [plage])
            def produit = Produit.build(prix: 26, fraisPort: fraisPort, poids: 0)

//            def plage2 = PlageFraisPort.build(debut: 0, fin: 100, prix: 2)
//            def fraisPort2 = FraisPort.build(type: "fixe", plages: [plage2])
            def produit2 = Produit.build(prix: 27, fraisPort: fraisPort, poids: 0)


            def lignes = []
            def lignePanier = new LignePanier(produit: produit, quantite: 1)
            def lignePanier2 = new LignePanier(produit: produit2, quantite: 1)

        when:
            lignes.add(lignePanier)
            lignes.add(lignePanier2)
            panier.lignes = lignes

        then:
            Produit.count() == 2
            panier.lignes.size() == 2
            panier.totaux().totalProduits == 53
            panier.totaux().totalFraisPort == 20
            panier.totaux().totalTTC == 73
            panier.totaux().poidsTotal == 0
    }

    void "test panier 1 fixe et 1 colis"() {
        given: 
            def panier = new Panier()

		    def fraisPort = FraisPort.build(type: "fixe", plages: null)
            def plage = PlageFraisPort.build(debut: 0, fin: 0, prix: 20, fraisPort: fraisPort)
			fraisPort.addToPlages(plage)
            def produit = Produit.build(prix: 1.1, fraisPort: fraisPort, poids: 25)

            def fraisPort2 = FraisPort.build(type: "poids", plages: null, titre: "colis")
            def plage2 = PlageFraisPort.build(debut: 0, fin: 100, prix: 2, fraisPort: fraisPort2)
            fraisPort2.addToPlages(plage2)
            def produit2 = Produit.build(prix: 2.2, fraisPort: fraisPort2, poids: 45)


            def lignes = []
            def lignePanier = new LignePanier(produit: produit, quantite: 1)
            def lignePanier2 = new LignePanier(produit: produit2, quantite: 1)

        when:
            lignes.add(lignePanier)
            lignes.add(lignePanier2)
            panier.lignes = lignes

        then:
            FraisPort.count() == 2
            Produit.count() == 2
            panier.lignes.size() == 2
            panier.totaux().totalProduits == 3.3
            panier.totaux().totalFraisPort == 22
            panier.totaux().totalTTC == 25.3
            panier.totaux().poidsTotal == 45
    }

    void "test panier 1 fixe et 1 mini max"() {
    	given: 
    		def panier = new Panier()

            def fraisPort = FraisPort.build(type: "fixe")
            def plage = PlageFraisPort.build(debut: 0, fin: 0, prix: 20, fraisPort: fraisPort)
            fraisPort.addToPlages(plage)
            def produit = Produit.build(prix: 1.1, fraisPort: fraisPort, poids: 25)

            def fraisPort2 = FraisPort.build(type: "poids", titre: "mini max")
            def plage2 = PlageFraisPort.build(debut: 0, fin: 100, prix: 1, fraisPort: fraisPort2)
    		fraisPort2.addToPlages(plage2)
            def produit2 = Produit.build(prix: 2.2, fraisPort: fraisPort2, poids: 45)


            def lignes = []
            def lignePanier = new LignePanier(produit: produit, quantite: 1)
            def lignePanier2 = new LignePanier(produit: produit2, quantite: 1)

        when:
            lignes.add(lignePanier)
    		lignes.add(lignePanier2)
    		panier.lignes = lignes

		then:
			FraisPort.count() == 2
            Produit.count() == 2
			panier.lignes.size() == 2
			panier.totaux().totalProduits == 3.3
			panier.totaux().totalFraisPort == 21
			panier.totaux().totalTTC == 24.3
            panier.totaux().poidsTotal == 45
    }
	
	void "quand plus de 3 produits minimax prendre frais de port colis"() {
		given:
			def panier = new Panier()
			def fraisPortMiniMax = FraisPort.build(type: "poids", titre: "mini max")
			def plageMiniMax0 = PlageFraisPort.build(debut: 0, fin: 100, prix: 2.94, fraisPort: fraisPortMiniMax)
			def plageMiniMax1 = PlageFraisPort.build(debut: 101, fin: 250, prix: 3.94, fraisPort: fraisPortMiniMax)
			def plageMiniMax2 = PlageFraisPort.build(debut: 251, fin: 500, prix: 4.94, fraisPort: fraisPortMiniMax)
			def plageMiniMax3 = PlageFraisPort.build(debut: 501, fin: 1000, prix: 5.94, fraisPort: fraisPortMiniMax)

			[plageMiniMax0, plageMiniMax1, plageMiniMax2, plageMiniMax3].each {
				fraisPortMiniMax.addToPlages(it)
			}
			
			def fraisPortColis = FraisPort.build(type: "poids", titre: "colis")
			def plageColis0  = PlageFraisPort.build(debut: 0, 		fin: 250,   prix: 6.94,  fraisPort: fraisPortColis)
			def plageColis1  = PlageFraisPort.build(debut: 251, 	fin: 500,   prix: 7.94,  fraisPort: fraisPortColis)
			def plageColis2  = PlageFraisPort.build(debut: 501, 	fin: 750,   prix: 7.94,  fraisPort: fraisPortColis)
			def plageColis3  = PlageFraisPort.build(debut: 751, 	fin: 1000,  prix: 8.94,  fraisPort: fraisPortColis)
			def plageColis4  = PlageFraisPort.build(debut: 1001,	fin: 2000,  prix: 9.94, fraisPort: fraisPortColis)
			def plageColis5  = PlageFraisPort.build(debut: 2001,	fin: 3000,  prix: 10.94, fraisPort: fraisPortColis)
			def plageColis6  = PlageFraisPort.build(debut: 3001,	fin: 5000,  prix: 12.94, fraisPort: fraisPortColis)
			def plageColis7  = PlageFraisPort.build(debut: 5001,	fin: 7000,  prix: 15.94, fraisPort: fraisPortColis)
			def plageColis8  = PlageFraisPort.build(debut: 7001,	fin: 10000, prix: 18.94, fraisPort: fraisPortColis)
			def plageColis9  = PlageFraisPort.build(debut: 10001,	fin: 15000, prix: 20.94, fraisPort: fraisPortColis)
			def plageColis10 = PlageFraisPort.build(debut: 15001, 	fin: 30000, prix: 29.94, fraisPort: fraisPortColis)
			
			[plageColis0, plageColis1, plageColis2, plageColis3, plageColis4, plageColis5, plageColis6, plageColis7, plageColis8, plageColis9, plageColis10].each {
				fraisPortColis.addToPlages(it)
			}
			
			def produit = Produit.build(prix: 0.59, fraisPort: fraisPortMiniMax, poids: 102)

			def lignes = []
			def lignePanier = new LignePanier(produit: produit, quantite: 10)

		when:
			lignes.add(lignePanier)
			panier.lignes = lignes
			def plages = PlageFraisPort.createCriteria().list{
				eq "fraisPort", fraisPortColis
				lte "debut", lignePanier.quantite * produit.poids
				gte "fin", lignePanier.quantite * produit.poids
			}
			PlageFraisPort plage = plages.size() > 0 ? plages.first() : 0

		then:
			FraisPort.count() == 2
			Produit.count() == 1
			panier.lignes.size() == 1
			panier.totaux().totalProduits == (lignePanier.quantite * produit.prix).round(2)
			panier.totaux().poidsTotal == lignePanier.quantite * produit.poids
			panier.totaux().totalFraisPort == plage.prix.round(2)
			panier.totaux().totalTTC == (lignePanier.quantite * produit.prix + plage.prix).round(2)
	}

}
