<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
  <g:if test="${produit.id > 0}">
    <r:require module="fileuploader" />
  </g:if>
</head>
<body>
    
  <div class="jumbotron">

    <div class="container">
      <div class="row">
        <h2>${produit.id > 0 ? "Modifier" : "Créer"} un produit de la catégorie ${produit.categorie.titre.capitalize()}</h2>
      </div>

      <g:if test="${flash.message}">
        <div class="alert alert-danger">
          ${flash.message}
        </div>
      </g:if>

      <div class="row">
        <g:form name="editProduitForm" action="save" role="form">
          <g:hiddenField name="id" value="${produit.id}"/>

          <div class="form-group">
            <label for="titre">Titre</label>
            <g:textField name="titre" class="form-control" value="${produit.titre}" placeholder="titre"/>
          </div>

          <div class="form-group">
            <label for="description">Description</label>
            <g:textArea name="description" class="form-control" value="${produit.description}" rows="15"/>
          </div>

          <div class="form-group">
            <label for="categorie">Categorie</label>
            <g:set var="categories" value="${fr.shrimpsforall.Categorie.list(sort: 'position', order: 'asc')}"/>
            <g:select name="categorie" class="form-control" value="${produit.categorie.id}" from="${categories}" optionKey="id" optionValue="titre"/>
          </div>

          <div class="form-group">
            <label for="prix">Prix</label>
            <g:textField name="prix" class="form-control" value="${formatNumber(number: produit.prix, type: 'currency', currencyCode: 'EUR')}"/>
          </div>

          <div class="form-group">
            <label for="fraisPort">Frais de port</label>
            <g:textField name="fraisPort" class="form-control" value="${formatNumber(number: produit.fraisPort, type: 'currency', currencyCode: 'EUR')}"/>
          </div>
          <g:submitButton name="editProduitFormBtn" value="Enregistrer" class="btn btn-default"/>
        </g:form>
      </div>

      <div class="row">
        <h2>Photos</h2>
      </div>

      <g:if test="${produit.id > 0}">
        <div class="row">
          <uploader:uploader id="uploader"
              url="${[controller:'photo', action:'upload']}"
              params="${['id':produit.id]}">
            <uploader:onComplete>
              loadPhotos();  
            </uploader:onComplete>
          </uploader:uploader>
        </div>
        
        <div class="row" id="photos"></div>

        <g:javascript>
          $(document).ready(function() {loadPhotos();});
        </g:javascript>
      </g:if>

    </div>

  </div>

  <script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
  <g:javascript>
    tinymce.init({selector:'textarea', menu: {}, toolbar: "styleselect | bold italic underline | bullist numlist | link image media", plugins: "link, image, paste, media, advlist"});
  </g:javascript>
</body>
</html>