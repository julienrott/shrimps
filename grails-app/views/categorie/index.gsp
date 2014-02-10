<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>
    
  <div class="jumbotron">

    <div class="container">
      <h2>Catégories</h2>

      <g:each in="${categories}">
      	<div>${it.position} ${it.titre} <g:link action="edit" id="${it.id}">modifier</g:link> <g:link action="delete" id="${it.id}">supprimer</g:link></div>
      </g:each>

      <g:form name="newCategorieForm" action="save">
      	<g:hiddenField name="id" value="${categorie?.id}"/>
      	<g:textField name="titre" placeholder="titre" value="${categorie?.titre}"/>
      	<g:textField name="position" placeholder="position" value="${categorie?.position}"/>
      	<g:submitButton name="newCategorieFormBtn" value="Enregistrer"/>
      	<g:if test="${flash.message}">
      		<div class="alert alert-danger">${flash.message}</div>
      	</g:if>
      </g:form>

    </div>

  </div>
    
</body>
</html>