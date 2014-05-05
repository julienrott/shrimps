<%@ page contentType="text/html"%>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="width=device-width, initial-scale=1" name="viewport">
	<title><g:layoutTitle default="Shrimps For All" /></title>
	<r:require module="bootstrapCSS" />
	<g:layoutHead />
	<r:layoutResources />
</head>
<body>
	<div class="container container-main">
		<div class="row">
			<g:link uri="/">
            	<r:img dir="images" file="logo.jpg" width="120" height="100" class="img col-md- img-thumbnail header-logo"/>
          	</g:link>
		</div>
		<div class="row">
			<g:layoutBody />
		</div>
	</div>

	<footer>
		<p>
			© Shrimps For All
			${new Date().format('yyyy')}
		</p>
	</footer>

	<r:layoutResources />
</body>
</html>
