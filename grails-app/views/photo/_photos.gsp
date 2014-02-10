<g:each in="${photos}" var="photo">
	<div class="col-md-6">
		<sec:ifAllGranted roles="ROLE_ADMIN">		
			<span id="${photo.id}" class="glyphicon glyphicon-remove-circle deleteIcon" title="Supprimer"></span>
		</sec:ifAllGranted>
		<img id="img_${photo.id }" class="img-responsive img-thumbnail" 
			 src="${createLink(controller:'photo', action:'showPhoto', id:"$photo.id", params:[type:'small'])}"/>
	</div>
</g:each>