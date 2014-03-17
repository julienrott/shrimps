<%@ page import="fr.shrimpsforall.*" %>
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
            <g:textArea name="description" class="form-control" value="${produit.description}" rows="10"/>
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
            <g:select name="fraisPort" class="form-control" from="${FraisPort.list()}" optionKey="id" optionValue="titre" value="${produit.fraisPort?.id}" noSelection="['':'-Choisir un frais de port-']"/>
          </div>
          
          <div class="form-group">
            <label for="poids">Poids en grammes</label>
            <g:textField name="poids" class="form-control" value="${produit.poids}" placeholder="poids"/>
          </div>

          <div class="form-group">
            <label for="stock">Stock</label>
            <g:textField name="stock" class="form-control" value="${produit.stock}" placeholder="stock"/>
          </div>

          <g:submitButton name="editProduitFormBtn" value="Enregistrer" class="btn btn-primary"/>
        </g:form>
      </div>

      <g:if test="${produit.id > 0}">

        <div class="row">
          <h2>Lots</h2>

          <g:each in="${produit?.lots}" var="lot">
            <g:form name="updateLotForm${lot.id}" id="${lot.id}">
              <g:textField name="titre" placeholder="titre" value="${lot.titre}"/>
              <g:textField name="prix" placeholder="prix" value="${formatNumber(number: lot.prix, type: 'currency', currencyCode: 'EUR')}"/>
              <g:submitButton name="_action_updateLot" value="Enregistrer" class="btn btn-primary"/>
              <g:submitButton name="_action_deleteLot" value="Supprimer" class="btn btn-danger"/>
            </g:form>
          </g:each>

          <g:form name="addLotForm">
            <g:hiddenField name="produit.id" value="${produit.id}"/>
            <g:textField name="titre" placeholder="titre"/>
            <g:textField name="prix" placeholder="prix"/>
            <g:submitButton name="_action_addLot" value="Ajouter" class="btn btn-primary"/>
          </g:form>
        </div>

        <div class="row">
          <h2>Photos</h2>
        </div>

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