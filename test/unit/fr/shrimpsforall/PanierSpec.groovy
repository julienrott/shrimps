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
            def produit = Produit.build(prix: 1.1, fraisPort: fraisPort, poids: 25)

            def plage2 = PlageFraisPort.build(debut: 0, fin: 100, prix: 2)
            def fraisPort2 = FraisPort.build(type: "fixe", plages: [plage2])
            def produit2 = Produit.build(prix: 2.2, fraisPort: fraisPort2, poids: 45)


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
            panier.totaux().totalProduits == 3.3
            panier.totaux().totalFraisPort == 20
            panier.totaux().totalTTC == 23.3
            panier.totaux().poidsTotal == 0
    }

    void "test panier 1 fixe et 1 colis"() {
        given: 
            def panier = new Panier()

            def plage = PlageFraisPort.build(debut: 0, fin: 0, prix: 20)
            def fraisPort = FraisPort.build(type: "fixe", plages: [plage])
            def produit = Produit.build(prix: 1.1, fraisPort: fraisPort, poids: 25)

            def plage2 = PlageFraisPort.build(debut: 0, fin: 100, prix: 2)
            def fraisPort2 = FraisPort.build(type: "poids", plages: [plage2], titre: "colis")
            def produit2 = Produit.build(prix: 2.2, fraisPort: fraisPort2, poids: 45)


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
            panier.totaux().totalProduits == 3.3
            panier.totaux().totalFraisPort == 22
            panier.totaux().totalTTC == 25.3
            panier.totaux().poidsTotal == 45
    }

    void "test panier 1 fixe et 1 mini max"() {
    	given: 
    		def panier = new Panier()

            def plage = PlageFraisPort.build(debut: 0, fin: 0, prix: 20)
            def fraisPort = FraisPort.build(type: "fixe", plages: [plage])
            def produit = Produit.build(prix: 1.1, fraisPort: fraisPort, poids: 25)

            def plage2 = PlageFraisPort.build(debut: 0, fin: 100, prix: 1)
            def fraisPort2 = FraisPort.build(type: "poids", plages: [plage2], titre: "mini max")
    		def produit2 = Produit.build(prix: 2.2, fraisPort: fraisPort2, poids: 45)


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
			panier.totaux().totalProduits == 3.3
			panier.totaux().totalFraisPort == 21
			panier.totaux().totalTTC == 24.3
            panier.totaux().poidsTotal == 45
    }

}
