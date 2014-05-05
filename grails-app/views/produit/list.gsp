<%@page defaultCodec="none" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
  <title>Shrimps For All : ${titre.capitalize()}</title>
  <r:require modules="fancyboxCSS,fancyboxJS"/>
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <sec:ifAllGranted roles="ROLE_ADMIN"><h2>${titre.capitalize()} <g:link action="add" params="[categorie: "${titre}"]"><span class="glyphicon glyphicon-plus"></span></g:link></h2></sec:ifAllGranted>
    </div>

    <div class="row">
      <g:each in="${produits}" var="produit" status="i">
      
        <g:render template="listcard" collection="${produit}"/>

        <g:if test="${(i+1)%3==0}">
          </div>
          <hr/>
          <div class="row">
        </g:if>

      </g:each>
    </div>

  </div>
  
  <g:javascript>
	$(document).ready(function() {
	  $(".fancybox")
	    .attr('rel', 'gallery')
	    .fancybox({
	        type: 'image',
	        padding: 10,
	        margin: 10,
	        openEffect: 'elastic',
	        closeEffect: 'elastic',
	        helpers: {title: {type: 'inside'}},
	        autoCenter : false,
	        aspectRatio: true,
	        fitToView: true
	    });
	});
  </g:javascript>

</body>
</html>