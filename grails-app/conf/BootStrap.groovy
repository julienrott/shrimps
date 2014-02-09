import fr.shrimpsforall.*

class BootStrap {

    def init = { servletContext ->

    	def roleAdmin = Role.findByAuthority("ROLE_ADMIN")
    	if(!roleAdmin) {
    		roleAdmin = new Role(authority: "ROLE_ADMIN").save()
    	}

    	def userAdmin = User.findByUsername("admin")
    	if(!userAdmin) {
    		userAdmin = new User(username: "admin", password: "admin").save()
    		UserRole.create(userAdmin, roleAdmin, true)
    	}
    }
    def destroy = {
    }
}
