<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<meta content="IE=edge" http-equiv="X-UA-Compatible">
		<meta content="width=device-width, initial-scale=1" name="viewport">
		<meta content="" name="description">
		<meta content="" name="author">
		<link href="../../assets/ico/favicon.ico" rel="shortcut icon">
		<title><g:layoutTitle default="Shrimps For All"/></title>
		<r:require module="bootstrapCSS"/>
		<g:layoutHead/>
		<g:javascript library="application"/>
		<g:javascript>
		  //var urlContext = '${grailsApplication.config.grails.serverURL}';
			var urlContext = "${createLink(uri: "/")}";
		</g:javascript>
		<r:layoutResources />
    <meta name="google-site-verification" content="VHf7mW_HJu8G2VfN7ZhaHihw95FnLcR-hZsCQUK9ob8" />
	</head>
	<body>

    <div style="display: none;" itemscope
        itemtype="http://schema.org/LocalBusiness">
      
      <span itemprop="name">Shrimps For All</span>
      <span itemprop="description">Shrimps For All (SFA, shrimpsforall) vous propose un large choix de crevettes, nourritures haut de gamme et matériels avec en exclusités des produits inédits sur notre site web.</span>
      <div itemprop="address" itemscope
          itemtype="http://schema.org/PostalAddress">
        <span itemprop="streetAddress">9 rue Sengenwald</span>
        <span itemprop="addressLocality">STRASBOURG</span>
        <span itemprop="postalCode">67000</span>
        <span itemprop="addressCountry">France</span>
      </div>
      <span itemprop="telephone">06 52 45 44 55</span>
    </div>

	<div class="navbar navbar-default navbar-fixed-top top-header" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <g:link uri="/">
            <r:img dir="images" file="logo.jpg" width="120" height="100" class="img col-md- img-thumbnail header-logo"/>
          </g:link>
        </div>

        <div class="navbar-collapse collapse">

          <div class="col-md-offset-1 col-md-3 header-banner">
            <r:img dir="images" file="baniere_plate.png" height="70"/>
          </div>

          <div id="logindiv" class="navbar-right">
            <sec:ifLoggedIn>
               Bonjour <sec:username/>
               <g:link controller="commande" action="mescommandes">Mes commandes</g:link>
               (<g:link uri='/j_spring_security_logout'>Se Déconnecter</g:link>)
               <sec:ifAllGranted roles="ROLE_ADMIN"><button onclick="TogetherJS(this); return false;">Start TogetherJS</button></sec:ifAllGranted>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
              <g:set value="/j_spring_security_check" var="url"/>
              <g:formRemote name="loginForm" url="[uri: url]" onSuccess="loginSuccess(data,textStatus);" onFailure="loginFailure(XMLHttpRequest,textStatus,errorThrown);" onComplete="loginComplete(XMLHttpRequest,textStatus);" before="loginClick()">
                <g:textField name="j_username" placeholder="email"/>
                <g:passwordField name="j_password" placeholder="mot de passe"/>
                <g:submitButton name="loginbtn" value="Se connecter" class="btn btn-primary"/>
                <g:link controller="home" action="createAccount" class="h6">Créer un compte</g:link>
              </g:formRemote>
              <div id="loginError" class="alert alert-danger" style="display: none;"></div>
            </sec:ifNotLoggedIn>
          </div>

          <div id="panierdiv" class="navbar-right" style="clear: right;">
            <g:render template="/panier/panier"/>
          </div>

        </div><!--/.navbar-collapse -->
      </div>

    </div>
    
    <div class="container container-main">
      <div class="row">

        <div class="col-md-2">
          <sec:ifAllGranted roles="ROLE_ADMIN">
            <div class="well">
              <h4>ADMIN</h4>
              <ul class="nav nav-pills nav-stacked">
                <li><g:link controller="homePageSlider" action="edit">Accueil</g:link></li>
                <li><g:link controller="homePageSlider">Photos Accueil</g:link></li>
                <li><g:link controller="categorie">Catégories de produits</g:link></li>
                <li><g:link controller="fraisPort">Frais de port</g:link></li>
                <li><g:link controller="pageInfo">Pages Info</g:link></li>
                <li><g:link controller="config">Configuration</g:link></li>
                <li><g:link controller="home" action="clients">Clients</g:link></li>
                <li><g:link controller="commande" action="encreation">Commandes en création</g:link></li>
                <li><g:link controller="commande" action="aexpedier">Commandes à expédier</g:link></li>
                <li><g:link controller="commande" action="expediees">Commandes expédiées</g:link></li>
              </ul>
            </div>
          </sec:ifAllGranted>

          <ul class="nav nav-pills nav-stacked">
            <g:each in="${fr.shrimpsforall.Categorie.list(sort: 'position', order: 'asc').grep{it.titre != "invisible"}}">
              <li class="${titre?.equals(it.titre)?'active':''}"><g:link uri="/${it.titre}">${it.titre}</g:link></li>
            </g:each>

            <hr>
            
            <g:each in="${fr.shrimpsforall.PageInfo.list(sort: 'position', order: 'asc')}">
              <li class="${titre?.equals(it.titre)?'active':''}"><g:link uri="/infos/${it.titre}">${it.titre}</g:link></li>
            </g:each>
          </ul>
        </div>

        <div class="col-md-8 border-left">

          <g:layoutBody/>
          
        </div>
        
        <iframe class="col-md-2" src="//www.facebook.com/plugins/likebox.php?href=https%3A%2F%2Fwww.facebook.com%2Fshrimpsforall&amp;width&amp;height=590&amp;colorscheme=light&amp;show_faces=true&amp;header=true&amp;stream=true&amp;show_border=true&amp;appId=305753002839441" scrolling="no" frameborder="0" style="border:none; overflow:hidden; height:590px;" allowTransparency="true"></iframe>
        

      </div>

      <hr>

      <footer>
        <p>© Shrimps For All ${new Date().format('yyyy')}</p>
      </footer>
    </div>

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <r:require modules="bootstrapJS,application"/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
		  <script src="https://togetherjs.com/togetherjs-min.js"></script>
    </sec:ifAllGranted>
    
    <g:javascript>$(document).ready(function(){initPanier();});</g:javascript>

    <g:if env="production">
      <g:javascript>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
        ga('create', 'UA-3781580-5', 'shrimpsforall.fr');
        ga('require', 'displayfeatures');
        ga('send', 'pageview');
      </g:javascript>
    </g:if>

    <div id="fb-root"></div>
    <script>(function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = "//connect.facebook.net/fr_FR/all.js#xfbml=1&appId=305753002839441";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>

		<r:layoutResources />
	</body>
</html>
