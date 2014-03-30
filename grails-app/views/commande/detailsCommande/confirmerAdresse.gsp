<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<h2>Commande du ${formatDate(date: commande.dateCreated, format: "dd/MM/yyyy")}</h2>
	</div>

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
        		<g:textField name="nom" placeholder="Nom" class="form-control" value="${commande?.nom}"/>
        	</div>

  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'prenom', 'alert alert-danger')}">
            	<label for="prenom">Prénom</label>
        		<g:textField name="prenom" placeholder="Prénom" class="form-control" value="${commande?.prenom}"/>
        	</div>
    	</div>

		<div class="row">
  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'adresse.ligne1', 'alert alert-danger')}">
            	<label for="ligne1">Adresse</label>
        		<g:textField name="ligne1" placeholder="Adresse" class="form-control" value="${commande?.adresse}"/>
        	</div>

  			<div class="form-group col-md-6">
            	<label for="ligne2">Complément adresse</label>
        		<g:textField name="ligne2" placeholder="Complément adresse" class="form-control" value="${commande?.complAdresse}"/>
        	</div>
    	</div>

		<div class="row">
  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'adresse.codePostal', 'alert alert-danger')}">
            	<label for="codePostal">Code postal</label>
        		<g:textField name="codePostal" placeholder="Code postal" class="form-control" value="${commande?.codePostal}"/>
        	</div>

  			<div class="form-group col-md-6 ${hasErrors(bean:user, field:'adresse.ville', 'alert alert-danger')}">
            	<label for="ville">Ville</label>
        		<g:textField name="ville" placeholder="Ville" class="form-control" value="${commande?.ville}"/>
        	</div>
    	</div>

		<div class="row">
    		<g:submitButton name="createAdressebtn" value="Valider mon adresse" event="payer" class="btn btn-primary col-md-offset-9"/>
    	</div>
	</g:form>

</body>
</html>