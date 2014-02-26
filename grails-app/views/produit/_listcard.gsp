<%@page defaultCodec="none" %>
<div class="col-md-4">

  <div class="row">
    <h3 class="col-md-11">${produit.titre.capitalize()}</h3>
  </div>

  <div class="row">
    <g:if test="${produit.photos[0]}">
      <img id="img_${produit.photos[0]?.id}" class="img-responsive img-thumbnail col-md-9 col-md-offset-1" src="${createLink(controller:'photo', action:'showPhoto', id:"${produit.photos[0]?.id}", params:[type:'small'])}"/>
    </g:if>
  </div>

  <br/>

  <div class="row">
    <div class="panel-group col-md-11" id="accordion${produit.id}">

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion${produit.id}" href="#collapse${produit.id}">
              Description <span id="icon-${produit.id}" class="glyphicon glyphicon-chevron-down"></span>
            </a>
          </h4>
        </div>
        <div id="collapse${produit.id}" data-id="${produit.id}" class="panel-collapse collapse">
          <div class="panel-body">
            ${produit.description.decodeHTML()}
          </div>
        </div>
      </div>

      <g:javascript>
        $('#collapse${produit.id}').on('hide.bs.collapse', function () {
          $("#icon-" + $(this).attr("data-id")).addClass("glyphicon-chevron-down");
          $("#icon-" + $(this).attr("data-id")).removeClass("glyphicon-chevron-up");
        })

        $('#collapse${produit.id}').on('show.bs.collapse', function () {
          $("#icon-" + $(this).attr("data-id")).addClass("glyphicon-chevron-up");
          $("#icon-" + $(this).attr("data-id")).removeClass("glyphicon-chevron-down");
        })
      </g:javascript>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            Prix: ${formatNumber(number: produit.prix, type: 'currency', currencyCode: 'EUR')}
          </h4>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            Port: ${formatNumber(number: produit.fraisPort, type: 'currency', currencyCode: 'EUR')}
          </h4>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <sec:ifAllGranted roles="ROLE_ADMIN">
              <g:link action="edit" id="${produit.id}">modifier</g:link>
              <g:link action="delete" id="${produit.id}">supprimer</g:link>
            </sec:ifAllGranted>
            <span class="glyphicon glyphicon-minus dec-quantite-panier" data-id="${produit.id}" style="cursor: pointer;"></span>
            <g:textField size="2" name="quantite_${produit.id}" value="1" class="quantite-panier" disabled="disabled"/>
            <span class="glyphicon glyphicon-plus inc-quantite-panier" data-id="${produit.id}" style="cursor: pointer;"></span>
            <a href="#" class="add-panier" data-id="${produit.id}">Ajouter au panier</a>
          </h4>
        </div>
      </div>

    </div>
  </div>

</div>