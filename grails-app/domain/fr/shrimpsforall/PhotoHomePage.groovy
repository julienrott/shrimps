package fr.shrimpsforall

class PhotoHomePage {
	
	String titre
	String description
	int position
	byte[] data
	byte[] data_small
	byte[] data_slider
	byte[] data_small_homepage
	
	static belongsTo = [homePageSlider: HomePageSlider]
	
	static constraints = {
		titre(nullable:true)
		description(nullable:true)
		data maxSize: 1024 * 1024 * 15 // 15MB
		data_small maxSize: 1024 * 1024 * 1 // 1MB
		data_slider maxSize: 1024 * 1024 * 1 // 1MB
		data_small_homepage maxSize: 1024 * 1024 * 1 // 1MB
	}
}