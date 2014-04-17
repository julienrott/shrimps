<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<table class="table table-striped">
	      <thead>
	        <tr>
	          <th>username/email</th>
	          <th>Nom</th>
	          <th>Prénom</th>
	          <th>Adresse</th>
	          <th>Inscription</th>
	          <th>Commandes</th>
	        </tr>
	      </thead>

	      <tbody>
	        <g:each in="${clients}">
	          <tr>
	            <td>${it.username}</td>
	            <td>${it.nom}</td>
	            <td>${it.prenom}</td>
	            <td>${it.adresse?.ligne1} ${it.adresse?.ligne2} ${it.adresse?.codePostal} ${it.adresse?.ville}</td>
	            <td><g:formatDate date="${it.dateCreated}" format="dd/MM/yyyy"/></td>
	            <td>
	            	<span class="label label-warning" title="création">${it.commandes.grep{it.statut == "création"}.size()}</span>
	            	<span class="label label-primary" title="payée">${it.commandes.grep{it.statut == "payée"}.size()}</span>
	            	<span class="label label-success" title="expédiée">${it.commandes.grep{it.statut == "expédiée"}.size()}</span>
	            </td>
	          </tr>
	        </g:each>
	      </tbody>

	    </table>
	</div>

</body>
</html>