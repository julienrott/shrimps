<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<g:set var="label" value="${commande.statut == "payée" ? "primary" : commande.statut == "expédiée" ? "success" : commande.statut == "création" ? "warning" : ""}"/>
		<h2>Commande du ${formatDate(date: commande.dateCreated, format: "dd/MM/yyyy")}

			<g:if test="${commande.statut == "payée"}">
				<span class="label label-${label}">Payée</span>
				<sec:ifAllGranted roles="ROLE_ADMIN">
					<g:link controller="commande" action="expediee" id="${commande.id}">
						<button class="btn btn-success">Expédier</button>
					</g:link>
				</sec:ifAllGranted>
			</g:if>

			<g:if test="${commande.statut == "expédiée"}">
				<span class="label label-${label}">Expédiée</span>
			</g:if>

		</h2>
	</div>

	<div class="row">
		<h3>Adresse</h3>

		<p>${commande?.nom} ${commande?.prenom}</p>
		<p>${commande?.adresse}</p>
		<p>${commande?.complAdresse}</p>
		<p>${commande?.codePostal} ${commande?.ville}</p>
	</div>

	<div class="row">
		<h3>Articles</h3>

	    <table class="table table-striped">
	      <thead>
	        <td>Produit</td>
	        <td>Quantité</td>
	        <td>Prix unitaire</td>
	        <td>Prix total</td>
	      </thead>
	      <tbody>
	        <g:each in="${commande?.lignes}">
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
	          <td>${formatNumber(number: commande?.totaux()?.totalProduits, type: 'currency', currencyCode: 'EUR')}</td>
	        </tr>
	        <tr class="warning">
	          <td colspan="2"></td>
	          <td>Total Frais de port</td>
	          <td>${formatNumber(number: commande?.fraisPort, type: 'currency', currencyCode: 'EUR')}</td>
	        </tr>
	        <tr class="danger">
	          <td colspan="2"></td>
	          <td>Total TTC</td>
	          <td>${formatNumber(number: commande?.totaux()?.totalTTC, type: 'currency', currencyCode: 'EUR')}</td>
	        </tr>
	      </tbody>
	    </table>
	</div>

	<g:if test="${commande.statut == "création" && !canPay}">
		<div class="row">
			<g:link event="confirmerAdresse" class="col-md-offset-8 col-md-1">
				<button class="btn btn-primary  col-md-offset-10">Confirmer l'adresse et payer</button>
			</g:link>
		</div>
	</g:if>

	<g:if test="${commande.statut == "création" && canPay}">
		<div class="row">
			<div>Vous avez la possibilité de payer votre commande en envoyant un chèque à l'adresse : Shrimpsforall, 9 rue Sengenwald, 67000 STRASBOURG. Votre commande sera traitée dès réception de celui-ci.</div>
			<form action="${grailsApplication.config.paypal.url}" method="post" class="col-md-offset-10 col-md-1">
				<!-- Identify your business so that you can collect the payments. -->
				<input type="hidden" name="business" value="${grailsApplication.config.paypal.receiver}">

				<!-- Specify a Buy Now button. -->
				<input type="hidden" name="cmd" value="_xclick">

				<input type="hidden" name="notify_url" value="${createLink(absolute: true, controller: 'panier', action: 'paypalresponse').replaceAll(/\?.*$/, "")}">
				<input type="hidden" name="return" value="${createLink(absolute: true, controller: 'panier', action: 'merci').replaceAll(/\?.*$/, "")}">
				<input type="hidden" name="invoice" value="${commande.id}">
				<input type="hidden" name="no_note" value="1">
				<input type="hidden" name="no_shipping" value="1">
				<input type="hidden" name="address_override" value="1">
				<input type="hidden" name="currency_code" value="EUR">

				<!-- Specify details about the item that buyers will purchase. -->
				<input type="hidden" name="item_name" value="Commande Shrimps For All">
				<input type="hidden" name="amount" value="${commande?.totaux()?.totalTTC}">
				<input type="hidden" name="currency_code" value="EUR">

				<!-- Display the payment button. -->
				<input type="image" name="submit" border="0" src="https://www.paypalobjects.com/fr_FR/i/btn/btn_paynow_LG.gif" alt="PayPal - The safer, easier way to pay online">
				<img alt="" border="0" width="1" height="1" src="https://www.paypalobjects.com/fr_FR/i/scr/pixel.gif" >
			</form>
		</div>
	</g:if>

</body>
</html>