<%@ page import="fr.shrimpsforall.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<h2>Frais de port</h2>
	</div>

	<g:if test="${flash.message}">
		<div class="alert alert-danger">${flash.message}</div>
	</g:if>

	<g:each in="${fraisPort}" var="fdp">
		
		<div class="row">
			<h4>${fdp.titre}</h4>

			<g:form name="newFraisPortForm${fdp.id}" action="save">
				<g:hiddenField name="id" value="${fdp.id}"/>
				<g:textField name="titre" value="${fdp.titre}"/>
				<g:select name="type" value="${fdp.type}" from="${fdp.constraints.type.inList}"/>
				<g:submitButton name="newFraisPortFormBtn${fdp.id}" value="Enregistrer"/>
				<g:link action="delete" id="${fdp.id}">supprimer</g:link>
			</g:form>
		</div>

		<div class="row col-md-offset-1">

			<div class="row">
				<h4>Plages de poids en grammes</h4>
			</div>

			<g:each in="${fdp.plages.sort{it.id}}" var="plage">
				<div class="row">
						
					<g:form name="newPlageForm${fdp.id}">
						<g:hiddenField name="idfdp" value="${fdp.id}"/>
						<g:hiddenField name="id" value="${plage.id}"/>
						<g:textField name="debut" value="${plage.debut}"/>
						<g:textField name="fin" value="${plage.fin}"/>
						<g:textField name="prix" value="${formatNumber(number: plage.prix, type: 'currency', currencyCode: 'EUR', locale: 'fr')}"/>
						<g:submitButton name="_action_savePlage" value="Enregistrer"/>
						<g:link action="deletePlage" id="${plage.id}">supprimer</g:link>
					</g:form>

				</div>
			</g:each>

			<div class="row">

				<g:form name="newPlageForm${fdp.id}">
					<g:hiddenField name="idfdp" value="${fdp.id}"/>
					<g:hiddenField name="id" value="0"/>
					<g:textField name="debut" placeholder="début"/>
					<g:textField name="fin" placeholder="fin"/>
					<g:textField name="prix" value="${formatNumber(number: 0, type: 'currency', currencyCode: 'EUR', locale: 'fr')}"/>
					<g:submitButton name="_action_savePlage" value="Créer"/>
				</g:form>

			</div>
			
		</div>

		<hr>
	</g:each>

	<div class="row">

		<g:form name="newFraisPortForm" action="save">
			<g:hiddenField name="id" value="0"/>
			<g:textField name="titre" placeholder="titre"/>
			<g:set var="newFraisPort" value="${new FraisPort()}"/>
			<g:select name="type" from="${newFraisPort.constraints.type.inList}"/>
			<g:submitButton name="newFraisPortFormBtn" value="Créer"/>
		</g:form>

	</div>

</body>
</html>