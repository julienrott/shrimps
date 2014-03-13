<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>
    
  <div class="jumbotron">

    <div class="container">
      <h2>Pages Info</h2>

      <g:each in="${pagesInfo}">
      	<div>${it.position} ${it.titre} <g:link action="index" id="${it.id}">modifier</g:link> <g:link action="delete" id="${it.id}">supprimer</g:link></div>
      </g:each>

      <g:form name="newPageInfoForm" action="save" role="form">
      	<g:hiddenField name="id" value="${pageInfo?.id}"/>
      	<g:textField name="titre" placeholder="titre" value="${pageInfo?.titre}"/>
      	<g:textField name="position" placeholder="position" value="${pageInfo?.position}"/>

        <div class="form-group">
          <g:textArea name="contenu" class="form-control" value="${pageInfo?.contenu}" rows="10"/>
        </div>

      	<g:submitButton name="newPageInfoFormBtn" value="Enregistrer"/>
      	<g:if test="${flash.message}">
      		<div class="alert alert-danger">${flash.message}</div>
      	</g:if>
      </g:form>

    </div>

  </div>
  
  <script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
  <g:javascript>
    tinymce.init({selector:'textarea', menu: {}, toolbar: "styleselect | bold italic underline | bullist numlist | link image media", plugins: "link, image, paste, media, advlist"});
  </g:javascript>
</body>
</html>