package fr.shrimpsforall

class HomePageSlider {

	static hasMany = [photos: PhotoHomePage]
	String accueil
	Produit top1
	Produit top2
	Produit top3
	Produit top4

	static mapping = {
		photos sort: 'position'
		accueil(type: "text")
	}

    static constraints = {
    	accueil(nullable: true)
    	top1(nullable: true)
    	top2(nullable: true)
    	top3(nullable: true)
    	top4(nullable: true)
    }
}
