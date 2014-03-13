package fr.shrimpsforall

class PageInfo {

	String titre
	int position
	String contenu

    static constraints = {
    }

    static mapping = {
		contenu(type: "text")
	}
}
