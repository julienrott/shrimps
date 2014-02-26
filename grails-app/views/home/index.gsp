<!DOCTYPE html>
<html lang="en">
<head>
	<meta content="main" name="layout">
</head>
<body>

	<div class="row">
		<div id="carousel-example-generic" class="carousel slide col-md-offset-1 col-md-10" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<g:each in="${homePageSlider?.photos}" var="photo" status="i">
					<li data-target="#carousel-example-generic" data-slide-to="${i}" class="${i==0?"active":""}"></li>
				</g:each>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<g:each in="${homePageSlider?.photos}" var="photo" status="i">
				<div class="item ${i==0?"active":""}">
					<img class="img-responsive col-md-offset-1 col-md-10" src="data:image/png;base64,${photo.data_slider.encodeBase64()}" alt="">
					<div class="carousel-caption">
					</div>
				</div>
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

	<g:javascript>
		$('.carousel').carousel({
			interval: 1500
		})
	</g:javascript>

</body>
</html>