<%@page defaultCodec="none" %>
<div class="col-md-4">

  <div class="row">
    <h3 class="col-md-11">${produit.titre.capitalize()}</h3>
  </div>

  <div class="row">
    <g:if test="${produit.photos[0]}">
      <img id="img_${produit.photos[0]?.id}" class="img-responsive img-thumbnail col-md-8 col-md-offset-1" src="${createLink(controller:'photo', action:'showPhoto', id:"${produit.photos[0]?.id}", params:[type:'small'])}"/>
    </g:if>
  </div>

  <div class="row col-md-11 col-md-offset-0" style="max-height: 200px; overflow: auto;">
    ${produit.description.decodeHTML()}
  </div>
  
  <div class="row col-md-11 col-md-offset-0" style="max-height: 200px; overflow: auto;">
    Prix: ${formatNumber(number: produit.prix, type: 'currency', currencyCode: 'EUR')}
  </div>

  <div class="row col-md-11 col-md-offset-0" style="max-height: 200px; overflow: auto;">
    Port: ${formatNumber(number: produit.fraisPort, type: 'currency', currencyCode: 'EUR')}
  </div>

  <div class="row col-md-11 col-md-offset-0">
      <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:link action="edit" id="${produit.id}">modifier</g:link>
        <g:link action="delete" id="${produit.id}">supprimer</g:link>
      </sec:ifAllGranted>
      <span class="glyphicon glyphicon-minus dec-quantite-panier" data-id="${produit.id}" style="cursor: pointer;"></span>
      <g:textField size="2" name="quantite_${produit.id}" value="1" class="quantite-panier" disabled="disabled"/>
      <span class="glyphicon glyphicon-plus inc-quantite-panier" data-id="${produit.id}" style="cursor: pointer;"></span>
      <a href="#" class="add-panier" data-id="${produit.id}">Ajouter au panier</a>
  </div>

</div>