package fr.shrimpsforall

class Photo {
	
	String titre
	byte[] data
	byte[] data_small
	byte[] data_slider
	byte[] data_small_homepage
	
	static belongsTo = [produit: Produit]
	
	static constraints = {
		titre(nullable:true)
		data maxSize: 1024 * 1024 * 15 // 15MB
		data_small maxSize: 1024 * 1024 * 1 // 1MB
		data_slider maxSize: 1024 * 1024 * 1 // 1MB
		data_small_homepage maxSize: 1024 * 1024 * 1 // 1MB
	}
}