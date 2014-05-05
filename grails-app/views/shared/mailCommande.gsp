<%@page defaultCodec="none" %>
<%@ page contentType="text/html"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<div>
		<g:link uri="/" absolute="true">
           	<r:img dir="images" file="logo.jpg" width="120" absolute="true" base="https://www.shrimpsforall.fr"/>
       	</g:link>
       	
		<g:link uri="/" absolute="true">
	       	<r:img dir="images" file="baniere_plate.png" height="70" absolute="true" base="https://www.shrimpsforall.fr"/>
       	</g:link>
	</div>
	
	<div>
		<p>&nbsp;</p>
	</div>
	
	<div>
		${msg.decodeHTML()}
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
	            <td>${formatNumber(number: it.lot ? it.lot.prix : it.produit.prix, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
        		<td>${formatNumber(number: it.lot ? it.lot.prix * it.quantite : it.produit.prix * it.quantite, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
	          </tr>
	        </g:each>
	        <tr><td colspan="4"></td></tr>
	        <tr class="info">
	          <td colspan="2"></td>
	          <td>Total Produits</td>
	          <td>${formatNumber(number: commande?.totaux()?.totalProduits, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
	        </tr>
	        <tr class="warning">
	          <td colspan="2"></td>
	          <td>Total Frais de port</td>
	          <td>${formatNumber(number: commande?.fraisPort, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
	        </tr>
	        <tr class="danger">
	          <td colspan="2"></td>
	          <td>Total TTC</td>
	          <td>${formatNumber(number: commande?.totaux()?.totalTTC, type: 'currency', currencyCode: 'EUR', locale: 'fr')}</td>
	        </tr>
	      </tbody>
	    </table>
	</div>

	
</body>
</html>
