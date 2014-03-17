<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
	<title>Shrimps For All : Je crée mon compte</title>
</head>
<body>
    
	<div class="container-fluid">

	<g:if test="${showBreadCrumb}">
		<div class="row">
			<button class="btn btn-primary col-md-3" disabled>Connection</button>
			<button class="btn btn-default col-md-3" disabled>Adresse</button>
			<button class="btn btn-default col-md-3" disabled>Paiement</button>
			<button class="btn btn-default col-md-3" disabled>Confirmation</button>
		</div>
	</g:if>

		<g:render template="/shared/createAccountForm"/>

	</div>
</body>
</html>