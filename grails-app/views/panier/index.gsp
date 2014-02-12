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

    <g:each in="${session.panier.lignes}">
      <div class="row">
        ${it.quantite} x ${it.produit.titre}
      </div>
    </g:each>

  </div>
    
</body>
</html>