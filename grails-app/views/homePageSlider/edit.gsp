<%@ page import="fr.shrimpsforall.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<g:form action="updateHomePage">

			<div class="form-group">
				<label for="accueil">accueil</label>
				<g:textArea name="accueil" class="form-control" value="${homePageSlider.accueil}" rows="15"/>
			</div>

			<div class="form-group">
				<label for="top1">top1</label>
				<g:select class="form-control" name="top1" value="${homePageSlider?.top1?.id}" from="${Produit.list().sort{it.titre}}" optionKey="id" optionValue="titre"/>
			</div>

			<div class="form-group">
				<label for="top2">top2</label>
				<g:select class="form-control" name="top2" value="${homePageSlider?.top2?.id}" from="${Produit.list().sort{it.titre}}" optionKey="id" optionValue="titre"/>
			</div>

			<div class="form-group">
				<label for="top3">top3</label>
				<g:select class="form-control" name="top3" value="${homePageSlider?.top3?.id}" from="${Produit.list().sort{it.titre}}" optionKey="id" optionValue="titre"/>
			</div>

			<div class="form-group">
				<label for="top4">top4</label>
				<g:select class="form-control" name="top4" value="${homePageSlider?.top4?.id}" from="${Produit.list().sort{it.titre}}" optionKey="id" optionValue="titre"/>
			</div>

			<g:submitButton name="updateHomePageBtn" value="enregistrer"/>
		</g:form>
	</div>


	<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
	<g:javascript>
	tinymce.init({selector:'textarea', menu: {}, toolbar: "styleselect | bold italic underline | bullist numlist | link image media", plugins: "link, image, paste, media, advlist"});
	</g:javascript>

</body>
</html>