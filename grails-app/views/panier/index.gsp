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
        <td>Frais de Port unitaire</td>
        <td>Frais de Port total</td>
      </thead>
      <tbody>
        <g:each in="${session.panier?.lignes}">
          <tr>
            <td>
              <g:link action="delrow" id="${it.produit.id}">
                <span class="glyphicon glyphicon-remove-circle" title="Supprimer"></span>
              </g:link>
            </td>
            <td>${it.produit.titre}</td>
            <td>
              <g:link action="dec" id="${it.produit.id}">
                <span class="glyphicon glyphicon-minus"></span>
              </g:link>
              ${it.quantite}
              <g:link action="inc" id="${it.produit.id}">
                <span class="glyphicon glyphicon-plus"></span>
              </g:link>
            </td>
            <td>${formatNumber(number: it.produit.prix, type: 'currency', currencyCode: 'EUR')}</td>
            <td>${formatNumber(number: it.produit.prix * it.quantite, type: 'currency', currencyCode: 'EUR')}</td>
            <td>${formatNumber(number: it.produit.fraisPort, type: 'currency', currencyCode: 'EUR')}</td>
            <td>${formatNumber(number: it.produit.fraisPort * it.quantite, type: 'currency', currencyCode: 'EUR')}</td>
          </tr>
        </g:each>
        <tr><td colspan="7"></td></tr>
        <tr class="info">
          <td colspan="5"></td>
          <td>Total Produits</td>
          <td>${formatNumber(number: session.panier?.totalProduits(), type: 'currency', currencyCode: 'EUR')}</td>
        </tr>
        <tr class="warning">
          <td colspan="5"></td>
          <td>Total Frais de port</td>
          <td>${formatNumber(number: session.panier?.totalFraisPort(), type: 'currency', currencyCode: 'EUR')}</td>
        </tr>
        <tr class="danger">
          <td colspan="5"></td>
          <td>Total TTC</td>
          <td>${formatNumber(number: session.panier?.totalTTC(), type: 'currency', currencyCode: 'EUR')}</td>
        </tr>
      </tbody>
    </table>

  </div>
    
</body>
</html>