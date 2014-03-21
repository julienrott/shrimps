<%@page defaultCodec="none" %>
<div class="col-md-4">

  <div class="row">
    <h4 class="col-md-11">${produit.titre.capitalize()}</h4>
  </div>

  <div class="row">
    <g:if test="${produit.photos[0]}">
      <r:img id="img_${produit.photos[0]?.id}" class="img-responsive img-thumbnail col-md-9 col-md-offset-1" uri="${createLink(controller:'photo', action:'showPhoto', id:"${produit.photos[0]?.id}", params:[type:'small'], absolute: true)}"/>
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

      <g:if test="${produit?.lots?.size() > 0}">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">

              <g:each in="${produit?.lots}" var="lot">
                <g:hiddenField name="lot-${lot.id}" value="${formatNumber(number: lot.prix, type: 'currency', currencyCode: 'EUR')}"/>
              </g:each>

              <g:select name="lots-${produit.id}" data-id="${produit.id}" from="${produit.lots.sort{it.id}}" optionKey="id" optionValue="titre"/>

              <g:javascript>
                $("#lots-${produit.id}").on("change", function() {
                  var idProduit = $(this).attr("data-id");
                  var idLot = $(this).val();
                  var prixLot = $("#lot-" + idLot).val();
                  $("#prix-" + idProduit).html(prixLot);
                  console.log(idProduit);
                });
              </g:javascript>

            </h4>
          </div>
        </div>
      </g:if>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            Prix: <span id="prix-${produit.id}">${formatNumber(number: produit.lots.size() > 0 ? produit.lots.sort{it.id}.first().prix : produit.prix, type: 'currency', currencyCode: 'EUR')}</span>
          </h4>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <span class="glyphicon glyphicon-minus dec-quantite-panier" data-id="${produit.id}" style="cursor: pointer;"></span>
            <g:textField size="2" name="quantite_${produit.id}" value="1" class="quantite-panier" disabled="disabled"/>
            <span class="glyphicon glyphicon-plus inc-quantite-panier" data-id="${produit.id}" style="cursor: pointer;"></span>
            <a href="#" class="add-panier" data-id="${produit.id}" data-lots="${produit?.lots?.size() > 0}">Ajouter au panier</a>
          </h4>
        </div>

        <g:if test="${produit.stock > -1}">
          <div class="panel-body">
            <g:if test="${produit.stock == 0}"><h4><span class="label label-danger">En rupture</span></h4></g:if>
            <g:if test="${produit.stock > 0 && produit.stock <= 5}"><h4><span class="label label-warning">Stock limité</span></h4></g:if>
            <g:if test="${produit.stock > 5 && produit.stock <= 10}"><h4><span class="label label-success">En stock</span></h4></g:if>
            <g:if test="${produit.stock > 10}"><h4><span class="label label-primary">Coup de coeur</span></h4></g:if>
          </div>
        </g:if>

      </div>

      <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
                <g:link action="edit" id="${produit.id}">modifier</g:link>
                <g:link action="delete" id="${produit.id}">supprimer</g:link>
            </h4>
          </div>
        </div>
      </sec:ifAllGranted>

    </div>
  </div>

</div>