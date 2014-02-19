package fr.shrimpsforall

class User implements Serializable {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Adresse adresse
	String nom
	String prenom

	static hasMany = [commandes: Commande]

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true, email: true
		password blank: false
		adresse nullable: true
		nom nullable: true
		prenom nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
