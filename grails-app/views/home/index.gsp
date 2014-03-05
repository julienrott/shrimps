<%@page defaultCodec="none" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<div class="col-md-12">${homePageSlider.accueil.decodeHTML()}</div>
	</div>

	<div class="row">
		<div id="carousel-example-generic" class="carousel slide col-md-offset-1 col-md-10" data-ride="carousel" style="max-height: 300px;">
			<!-- Indicators -->
			<!--<ol class="carousel-indicators">
				<g:each in="${homePageSlider?.photos}" var="photo" status="i">
					<li data-target="#carousel-example-generic" data-slide-to="${i}" class="${i==0?"active":""}"></li>
				</g:each>
			</ol>-->

			<!-- Wrapper for slides -->
			<div class="carousel-inner" style="max-height: 300px;">
				<g:each in="${homePageSlider?.photos}" var="photo" status="i">
					<g:if test="${homePageSlider?.photos[i]}">
						<div class="item ${i==0?"active":""}">
							
							<img class="img-responsive col-md-offset-1 col-md-5" src="data:image/png;base64,${homePageSlider?.photos[i]?.data_slider?.encodeBase64()}" alt="" style="max-height: 300px;">
							
							<g:set var="i" value="${i++}"/>
							
							<img class="img-responsive col-md-offset-0 col-md-5" src="data:image/png;base64,${homePageSlider?.photos[i]?.data_slider?.encodeBase64()}" alt="" style="max-height: 300px; margin-left: -15px;">
							
						</div>
					</g:if>
				</g:each>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
			<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
	</div>

	<div class="row">
		<h3>Nos top ventes</h3>
	</div>

	<div class="row">

		<div class="col-md-3">
			<div class="col-md-offset-1"><h4>${homePageSlider.top1.titre}</h4></div>
			
			<g:if test="${homePageSlider.top1.photos[0]}">
		      <img id="img_${homePageSlider.top1.photos[0]?.id}" class="img-responsive img-thumbnail col-md-9 col-md-offset-1" src="${createLink(controller:'photo', action:'showPhoto', id:"${homePageSlider.top1.photos[0]?.id}", params:[type:'small'])}"/>
		    </g:if>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-offset-1"><h4>${homePageSlider.top2.titre}</h4></div>
			
			<g:if test="${homePageSlider.top2.photos[0]}">
		      <img id="img_${homePageSlider.top2.photos[0]?.id}" class="img-responsive img-thumbnail col-md-9 col-md-offset-1" src="${createLink(controller:'photo', action:'showPhoto', id:"${homePageSlider.top2.photos[0]?.id}", params:[type:'small'])}"/>
		    </g:if>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-offset-1"><h4>${homePageSlider.top3.titre}</h4></div>
			
			<g:if test="${homePageSlider.top3.photos[0]}">
		      <img id="img_${homePageSlider.top3.photos[0]?.id}" class="img-responsive img-thumbnail col-md-9 col-md-offset-1" src="${createLink(controller:'photo', action:'showPhoto', id:"${homePageSlider.top3.photos[0]?.id}", params:[type:'small'])}"/>
		    </g:if>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-offset-1"><h4>${homePageSlider.top4.titre}</h4></div>
			
			<g:if test="${homePageSlider.top4.photos[0]}">
		      <img id="img_${homePageSlider.top4.photos[0]?.id}" class="img-responsive img-thumbnail col-md-9 col-md-offset-1" src="${createLink(controller:'photo', action:'showPhoto', id:"${homePageSlider.top4.photos[0]?.id}", params:[type:'small'])}"/>
		    </g:if>
		</div>

	</div>

	<g:javascript>
		$('.carousel').carousel({
			interval: 3000
		})
	</g:javascript>

</body>
</html>