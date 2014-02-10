<%@page defaultCodec="none" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>
    
  <div class="jumbotron">

    <div class="container">

      <div class="row">
        <h2>${titre.capitalize()} <sec:ifAllGranted roles="ROLE_ADMIN"><g:link action="add" params="[categorie: "${titre}"]"><span class="glyphicon glyphicon-plus"></span></g:link></sec:ifAllGranted></h2>
      </div>

      <g:each in="${produits}">
        <div class="row">
          <h3>${it.titre} <sec:ifAllGranted roles="ROLE_ADMIN"><g:link action="edit" id="${it.id}">modifier</g:link> <g:link action="delete" id="${it.id}">supprimer</g:link></sec:ifAllGranted></h3>
        </div>
        <div class="row">
          ${it.description.decodeHTML()}
        </div>
        <div class="row">
          <g:if test="${it.photos[0]}">
            <img id="img_${it.photos[0]?.id}" class="img-responsive img-thumbnail col-md-6" src="${createLink(controller:'photo', action:'showPhoto', id:"${it.photos[0]?.id}", params:[type:'small'])}"/>
          </g:if>
          <g:if test="${it.photos[1]}">
            <img id="img_${it.photos[1]?.id}" class="img-responsive img-thumbnail col-md-6" src="${createLink(controller:'photo', action:'showPhoto', id:"${it.photos[1]?.id}", params:[type:'small'])}"/>
          </g:if>
        </div>

        <hr/>

      </g:each>

    </div>

  </div>
    
</body>
</html>