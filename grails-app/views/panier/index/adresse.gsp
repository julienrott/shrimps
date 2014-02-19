<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
	<title>Shrimps For All : Mon Panier</title>
</head>
<body>
    
	<div class="container-fluid">
  		<div class="row">
			<button class="btn btn-default col-md-3" disabled>Connection</button>
			<button class="btn btn-primary col-md-3" disabled>Adresse</button>
			<button class="btn btn-default col-md-3" disabled>Paiement</button>
			<button class="btn btn-default col-md-3" disabled>Confirmation</button>
		</div>

		<div>&nbsp;</div>

		<g:hasErrors bean="${user}">
			<div class="row">
			<ul class="alert alert-danger">
				<g:if test="${message}">
					<li>${message}</li>
				</g:if>
				<g:eachError var="err" bean="${user}">
			    	<li><g:message error="${err}" /></li>
				</g:eachError>
			</ul>
			</div>
		</g:hasErrors>

		<g:form name="createAdresseForm" >
			
			<div class="row">
	  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'nom', 'alert alert-danger')}">
	            	<label for="nom">Nom</label>
	        		<g:textField name="nom" placeholder="Nom" class="form-control" value="${user?.nom}"/>
	        	</div>

	  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'prenom', 'alert alert-danger')}">
	            	<label for="prenom">Prénom</label>
	        		<g:textField name="prenom" placeholder="Prénom" class="form-control" value="${user?.prenom}"/>
	        	</div>
        	</div>

			<div class="row">
	  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'adresse.ligne1', 'alert alert-danger')}">
	            	<label for="ligne1">Adresse</label>
	        		<g:textField name="ligne1" placeholder="Adresse" class="form-control" value="${user?.adresse?.ligne1}"/>
	        	</div>

	  			<div class="form-group col-md-6">
	            	<label for="ligne2">Complément adresse</label>
	        		<g:textField name="ligne2" placeholder="Complément adresse" class="form-control" value="${user?.adresse?.ligne2}"/>
	        	</div>
        	</div>

			<div class="row">
	  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'adresse.codePostal', 'alert alert-danger')}">
	            	<label for="codePostal">Code postal</label>
	        		<g:textField name="codePostal" placeholder="Code postal" class="form-control" value="${user?.adresse?.codePostal}"/>
	        	</div>

	  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'adresse.ville', 'alert alert-danger')}">
	            	<label for="ville">Ville</label>
	        		<g:textField name="ville" placeholder="Ville" class="form-control" value="${user?.adresse?.ville}"/>
	        	</div>
        	</div>

			<div class="row">
        		<g:submitButton name="createAdressebtn" value="Valider mon adresse" event="createAdresse" class="btn btn-primary col-md-offset-9"/>
        	</div>
		</g:form>
		
	</div>
</body>
</html>