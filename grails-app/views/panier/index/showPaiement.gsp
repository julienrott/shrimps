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

			<h3>Ma commande</h3>

		    <table class="table table-striped">
		      <thead>
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
		            <td>${it.produit.titre}</td>
		            <td>${it.quantite}</td>
		            <td>${formatNumber(number: it.produit.prix, type: 'currency', currencyCode: 'EUR')}</td>
		            <td>${formatNumber(number: it.produit.prix * it.quantite, type: 'currency', currencyCode: 'EUR')}</td>
		            <td>${formatNumber(number: it.produit.fraisPort, type: 'currency', currencyCode: 'EUR')}</td>
		            <td>${formatNumber(number: it.produit.fraisPort * it.quantite, type: 'currency', currencyCode: 'EUR')}</td>
		          </tr>
		        </g:each>
		        <tr><td colspan="6"></td></tr>
		        <tr class="info">
		          <td colspan="4"></td>
		          <td>Total Produits</td>
		          <td>${formatNumber(number: session.panier?.totalProduits(), type: 'currency', currencyCode: 'EUR')}</td>
		        </tr>
		        <tr class="warning">
		          <td colspan="4"></td>
		          <td>Total Frais de port</td>
		          <td>${formatNumber(number: session.panier?.totalFraisPort(), type: 'currency', currencyCode: 'EUR')}</td>
		        </tr>
		        <tr class="danger">
		          <td colspan="4"></td>
		          <td>Total TTC</td>
		          <td>${formatNumber(number: session.panier?.totalTTC(), type: 'currency', currencyCode: 'EUR')}</td>
		        </tr>
		      </tbody>
		    </table>

			<g:link event="retour">
				<button class="btn btn-warning  col-md-offset-10">Modifier</button>
			</g:link>

			<h3>Paiement</h3>

			<g:if test="${message}">
				<div class="alert alert-danger">${message}</div>
			</g:if>

			<form method="POST" id="braintree-payment-form" class="col-md-6">

				<div class="row">
					<div class="form-group">
		            	<label>Numéro de carte</label>
		        		<input type="text" placeholder="Numéro de carte" class="form-control" autocomplete="off" data-encrypted-name="number"/>
		        	</div>
	        	</div>
				
				<div class="row">
					<div class="form-group">
		            	<label>Date d'expiration (MM/AAAA)</label>
						<input type="text" size="2" name="month" /> / <input type="text" size="4" name="year" />
		        	</div>
	        	</div>
				
				<div class="row">
					<label>Code de vérification</label>
					<input type="text" size="4" autocomplete="off" data-encrypted-name="cvv" />
	        	</div>
				
				<div class="row col-md-offset-10">
        			<g:submitButton event="payer" class="btn btn-primary" value="Payer" id="paybtn"/>
        			<g:javascript>
        				$("#paybtn").on("click", function(){$(this).prop("disabled",true);});
        			</g:javascript>
	        	</div>
			</form>

		</div>
	</div>

	<script src="//js.braintreegateway.com/v1/braintree.js"></script>
	<g:javascript>
		var braintree = Braintree.create("MIIBCgKCAQEA0uQ/6LYZnlgz9UaPaZlA5nc3z5IyFaxpeNV3mOpIwQcGVS5+uA4699O2L2mciXT7Be55yEDhTWM/bN0JK9Zm35TGtUBb9AuP0Oa7lw/whwze3B4Rej/A5uEFQugYhTJL/7EVPq9kDvaKmB8FYp3L582mYRUn+oQyEJC4L5SkcHAck03e+BD18Ju+TnmH6wIK44iY/Vi7tgfcOcLqJ/SDh3WicqFQxNRrMNKIcmR+MQyZwTX1bX1vZf4RpB9kDPOpAjo6H0CkFaFiLr8j4qIUiv2cL2QhOEZmvAMeI2AOYvHMpQX3fwvHHv/n1utX72qunMnfDLsXjI/eiY5my9by4QIDAQAB");
     	braintree.onSubmitEncryptForm('braintree-payment-form');
	</g:javascript>

</body>
</html>