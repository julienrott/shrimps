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
			<div class="col-md-4" style="text-align:justify;">
				<h3><b>Payer par chèque</b></h3>
				Vous avez la possibilité de payer votre commande en envoyant un chèque à l'adresse : Shrimpsforall, 9 rue Sengenwald, 67000 STRASBOURG. Votre commande sera traitée dès réception de celui-ci.
			</div>

			<div class="col-md-5">
				<h3><b>Payer par Carte Bancaire</b></h3>
				<form method="POST" id="payment-form">
					<input type="hidden" name="_eventId" value="payerParCarte">
					
					<div class="form-row">
						<label>
							<span>Numéro de carte</span>
							<input type="text" size="20" data-stripe="number"/>
						</label>
					</div>

					<div class="form-row">
						<label>
							<span>Cryptogramme Visuel</span>
							<input type="text" size="4" data-stripe="cvc"/>
						</label>
					</div>

					<div class="form-row">
						<label>
							<span>Expiration (MM/AAAA)</span>
							<input type="text" size="2" data-stripe="exp-month"/>
						</label>
						<span> / </span>
						<input type="text" size="4" data-stripe="exp-year"/>
					</div>

					<div class="label label-danger payment-errors"></div>

					<div>
						<g:submitButton name="payerParCarte" value="Payer" event="payerParCarte" class="btn btn-primary"/>
						<span style="display: none;" class="btn btn-success" disabled="disabled" id="btnpaiementencours">Paiement en cours...</span>
					</div>
				</form>
			</div>

			<div class="col-md-2">
				<h3><b>Paypal</b></h3>
				<form action="${grailsApplication.config.paypal.url}" method="post">
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
		</div>

		<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
		<g:javascript>
			Stripe.setPublishableKey('${grailsApplication.config.stripe.publishable.key}');

			$(document).ready(function() {
				$('#payment-form').submit(function(event) {
					var form = $(this);
					//form.find('button').prop('disabled', true);
					form.find('#_eventId_payerParCarte').hide();
					form.find('#btnpaiementencours').show();
					Stripe.card.createToken(form, stripeResponseHandler);
					return false;
				});
			});

			var stripeResponseHandler = function(status, response) {
				var form = $('#payment-form');
				if (response.error) {
					form.find('.payment-errors').html(response.error.message);
					//form.find('button').prop('disabled', false);
					form.find('#_eventId_payerParCarte').show();
					form.find('#btnpaiementencours').hide();
				} else {
					var token = response.id;
					form.append($('<input type="hidden" name="stripeToken" />').val(token));
					form.get(0).submit();
				}
			};
		</g:javascript>

	</g:if>

</body>
</html>