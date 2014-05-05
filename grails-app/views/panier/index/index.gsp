<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
  <title>Shrimps For All : Mon Panier</title>
</head>
<body>
    
  <div class="container-fluid">
    <div class="row">
      <h1>Mon Panier</h1>
    </div>

    <table class="table table-striped">
      <thead>
        <td></td>
        <td>Produit</td>
        <td>Quantité</td>
        <td>Prix unitaire</td>
        <td>Prix total</td>
      </thead>
      <tbody>
        <g:each in="${session.panier?.lignes}">
          <tr>
            <td>
              <g:link action="delrow" id="${it.produit.id}" params="${[idLot: it.lot?.id?:0]}">
                <span class="glyphicon glyphicon-remove-circle" title="Supprimer"></span>
              </g:link>
            </td>
            <td>${it.lot?.titre} ${it.produit.titre}</td>
            <td>
              <g:link action="dec" id="${it.produit.id}" params="${[idLot: it.lot?.id?:0]}">
                <span class="glyphicon glyphicon-minus"></span>
              </g:link>
              ${it.quantite}
              <g:link action="inc" id="${it.produit.id}" params="${[idLot: it.lot?.id?:0]}">
                <span class="glyphicon glyphicon-plus"></span>
              </g:link>
            </td>
            <td>${formatNumber(number: it.lot ? it.lot.prix : it.produit.prix, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
            <td>${formatNumber(number: it.lot ? it.lot.prix * it.quantite : it.produit.prix * it.quantite, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
          </tr>
        </g:each>
        <tr><td colspan="5"></td></tr>
        <tr class="info">
          <td colspan="3"></td>
          <td>Total Produits</td>
          <td>${formatNumber(number: session.panier?.totaux()?.totalProduits, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
        </tr>
        <tr class="warning">
          <td colspan="3"></td>
          <td>Total Frais de port</td>
          <td>${formatNumber(number: session.panier?.totaux()?.totalFraisPort, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
        </tr>
        <tr class="danger">
          <td colspan="3"></td>
          <td>Total TTC</td>
          <td>${formatNumber(number: session.panier?.totaux()?.totalTTC, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
        </tr>
      </tbody>
    </table>

    <div class="row">
      <g:if test="${session.panier?.lignes?.size() > 0}">
        <g:link event="commander" disabled="disabled">
          <button type="button" class="btn btn-primary col-md-offset-10" ${session.panier?.lignes?.size() > 0 ? "" : "disabled"}>Commander</button>
        </g:link>
      </g:if>
      <g:else>
        <button type="button" class="btn btn-primary col-md-offset-10" ${session.panier?.lignes?.size() > 0 ? "" : "disabled"}>Commander</button>
      </g:else>
    </div>

  </div>
    
</body>
</html>