<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
	<r:require module="fileuploader" />
</head>
<body>
	<div class="row">
		<uploader:uploader id="uploader"
				url="${[controller:'homePageSlider', action:'upload']}">
			<uploader:onComplete>
				window.location.reload();  
			</uploader:onComplete>
		</uploader:uploader>
	</div>

	<g:each in="${homePageSlider?.photos}">
		<div class="row">
			<div class="col-md-6">
				<img class="img-responsive" src="data:image/png;base64,${it.data_small.encodeBase64()}"/>
			</div>

			<div class="col-md-6">
				<g:form name="form-${it.id}" action="save" params="${[id: it.id]}">
					position <g:textField placeHolder="position" name="position" value="${it.position}" size="3"/><br/>

					titre <g:textField placeHolder="titre" name="titre" value="${it.titre}"/><br/>
					
					description <g:textField placeHolder="description" name="description" value="${it.description}"/>

					<g:submitButton name="submit${it.id}" value="Enregistrer"/>
					<g:submitButton name="_action_delete" value="Supprimer" params="${it.id}"/>

				</g:form>
			</div>
		</div>
		<hr>
	</g:each>
</body>
</html>