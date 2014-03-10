<h4><g:link controller="panier">Mon Panier</g:link></h4>
<div>
	<g:if test="${session.panier?.quantiteProduits() > 1}">
		${session.panier.quantiteProduits()} articles<!-- pour un total de ${formatNumber(number: 0, type: 'currency', currencyCode: 'EUR')}-->
	</g:if>
	<g:elseif test="${session.panier?.quantiteProduits() > 0}">
		${session.panier.quantiteProduits()} article<!-- pour un total de ${formatNumber(number: 0, type: 'currency', currencyCode: 'EUR')}-->
	</g:elseif>
	<g:else>
		${session.panier?session.panier.quantiteProduits():0} article
	</g:else>
</div>