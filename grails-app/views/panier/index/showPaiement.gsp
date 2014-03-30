<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
	<title>Shrimps For All : Mon Panier</title>
</head>
<body>
    
	<div class="container-fluid">

  		<div class="row">
			<button class="btn btn-default col-md-3" disabled>Connection</button>
			<button class="btn btn-default col-md-3" disabled>Adresse</button>
			<button class="btn btn-primary col-md-3" disabled>Paiement</button>
			<button class="btn btn-default col-md-3" disabled>Confirmation</button>
		</div>

		<div>&nbsp;</div>

		<div class="row">
			<h3>Mon adresse</h3>

			<p>${user?.nom} ${user?.prenom}</p>
			<p>${user?.adresse?.ligne1}</p>
			<p>${user?.adresse?.ligne2}</p>
			<p>${user?.adresse?.codePostal} ${user?.adresse?.ville}</p>
		</div>

		<div class="row">
			<h3>Ma commande</h3>

		    <table class="table table-striped">
		      <thead>
		        <td>Produit</td>
		        <td>Quantité</td>
		        <td>Prix unitaire</td>
		        <td>Prix total</td>
		      </thead>
		      <tbody>
		        <g:each in="${session.panier?.lignes}">
		          <tr>
		            <td>${it.lot?.titre} ${it.produit.titre}</td>
		            <td>${it.quantite}</td>
		            <td>${formatNumber(number: it.lot ? it.lot.prix : it.produit.prix, type: 'currency', currencyCode: 'EUR')}</td>
            		<td>${formatNumber(number: it.lot ? it.lot.prix * it.quantite : it.produit.prix * it.quantite, type: 'currency', currencyCode: 'EUR')}</td>
		          </tr>
		        </g:each>
		        <tr><td colspan="4"></td></tr>
		        <tr class="info">
		          <td colspan="2"></td>
		          <td>Total Produits</td>
		          <td>${formatNumber(number: session.panier?.totaux()?.totalProduits, type: 'currency', currencyCode: 'EUR')}</td>
		        </tr>
		        <tr class="warning">
		          <td colspan="2"></td>
		          <td>Total Frais de port</td>
		          <td>${formatNumber(number: session.panier?.totaux()?.totalFraisPort, type: 'currency', currencyCode: 'EUR')}</td>
		        </tr>
		        <tr class="danger">
		          <td colspan="2"></td>
		          <td>Total TTC</td>
		          <td>${formatNumber(number: session.panier?.totaux()?.totalTTC, type: 'currency', currencyCode: 'EUR')}</td>
		        </tr>
		      </tbody>
		    </table>
		</div>

		<div class="row">
			<g:if test="${message}">
				<div class="alert alert-danger">${message}</div>
			</g:if>

			
			<g:link event="retour" class="col-md-offset-8 col-md-1">
				<button class="btn btn-warning  col-md-offset-10">Modifier</button>
			</g:link>

			<form action="${grailsApplication.config.paypal.url}" method="post" class="col-md-offset-1 col-md-1">

				<!-- Identify your business so that you can collect the payments. -->
				<input type="hidden" name="business" value="${grailsApplication.config.paypal.receiver}">

				<!-- Specify a Buy Now button. -->
				<input type="hidden" name="cmd" value="_xclick">

				<input type="hidden" name="notify_url" value="${grailsApplication.config.paypal.notify.url}">
				<input type="hidden" name="return" value="${grailsApplication.config.paypal.return.url}">
				<input type="hidden" name="invoice" value="${}">
				<input type="hidden" name="no_note" value="1">
				<input type="hidden" name="no_shipping" value="1">
				<input type="hidden" name="address_override" value="1">
				<input type="hidden" name="currency_code" value="EUR">

				<!-- Specify details about the item that buyers will purchase. -->
				<input type="hidden" name="item_name" value="commande">
				<input type="hidden" name="amount" value="${session.panier?.totaux()?.totalTTC}">
				<input type="hidden" name="currency_code" value="EUR">

				<!-- Display the payment button. -->
				<input type="image" name="submit" border="0" src="https://www.paypalobjects.com/fr_FR/i/btn/btn_paynow_LG.gif" alt="PayPal - The safer, easier way to pay online">
				<img alt="" border="0" width="1" height="1" src="https://www.paypalobjects.com/fr_FR/i/scr/pixel.gif" >

			</form>
			
		</div>
	</div>
</body>
</html>