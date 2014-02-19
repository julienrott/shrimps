<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
	<title>Shrimps For All : Mon Panier</title>
</head>
<body>
    
	<div class="container-fluid">

		<div class="row">
			<button class="btn btn-primary col-md-3" disabled>Connection</button>
			<button class="btn btn-default col-md-3" disabled>Adresse</button>
			<button class="btn btn-default col-md-3" disabled>Paiement</button>
			<button class="btn btn-default col-md-3" disabled>Confirmation</button>
		</div>

		<div class="row">

			<div class="col-md-6">
				<h2>J'ai déjà un compte</h2>

				<g:if test="${message}">
					<div class="alert alert-danger">${message}</div>
				</g:if>

		  		<g:form name="loginForm" >
		  			<div class="form-group">
		            	<label for="j_username2">Email</label>
			        	<g:textField name="j_username2" placeholder="email" class="form-control"/>
		        	</div>

		        	<div class="form-group">
		            	<label for="j_password2">Mot de passe</label>
			        	<g:passwordField name="j_password2" placeholder="mot de passe" class="form-control"/>
		        	</div>

	    			<g:submitButton name="loginbtn" class="btn btn-primary" value="Se connecter" event="connect"/>

			    </g:form>
		    </div>
		    
			<div class="col-md-6">
				<h2>Je crée mon compte</h2>

        		<g:link event="showCreateAccount">
        			<button type="button" class="btn btn-primary">Créer mon compte</button>
    			</g:link>

		    </div>

	    </div>
	</div>
</body>
</html>