package fr.shrimpsforall

class HomePageSlider {

	static hasMany = [photos: PhotoHomePage]

	static mapping = {
		photos sort: 'position'
	}

    static constraints = {
    }
}
