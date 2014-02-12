<!DOCTYPE html>
<html lang="en">
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
	</head>
	<body>

		<div class="navbar navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <g:link uri="/">
            <r:img dir="images" file="logo.jpg" height="100"/>
          </g:link>
        </div>
        <div class="navbar-collapse collapse">

          <div id="logindiv" class="navbar-right">
            <sec:ifLoggedIn>
               Logged in as <sec:username/> (<g:link uri='/j_spring_security_logout'>Logout</g:link>)
               <sec:ifAllGranted roles="ROLE_ADMIN"><button onclick="TogetherJS(this); return false;">Start TogetherJS</button></sec:ifAllGranted>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
              <g:set value="/j_spring_security_check" var="url"/>
              <g:formRemote name="loginForm" url="[uri: url]" onSuccess="loginSuccess(data,textStatus);" onFailure="loginFailure(XMLHttpRequest,textStatus,errorThrown);" onComplete="loginComplete(XMLHttpRequest,textStatus);" before="loginClick()">
                <g:textField name="j_username" placeholder="email"/>
                <g:passwordField name="j_password" placeholder="mot de passe"/>
                <g:submitButton name="loginbtn" value="Se connecter" />
              </g:formRemote>
              <div id="loginError" class="alert alert-danger" style="display: none;"></div>
            </sec:ifNotLoggedIn>
          </div>

          <div id="panierdiv" class="navbar-right" style="clear: right;">
            <g:render template="/panier/panier"/>
          </div>

        </div><!--/.navbar-collapse -->
      </div>

      <hr>

    </div>
		
		<div class="container">
      <div class="row">
        
        <div class="col-md-2">
          <sec:ifAllGranted roles="ROLE_ADMIN">
            <div class="well">
              <h4>ADMIN</h4>
                <div><g:link controller="categorie">Catégories</g:link></div>
            </div>
          </sec:ifAllGranted>

          <div class="well">
            <h4>MENU</h4>
            <g:each in="${fr.shrimpsforall.Categorie.list(sort: 'position', order: 'asc')}">
            	<div><g:link uri="/${it.titre}">${it.titre}</g:link></div>
            </g:each>
          </div>
        </div>

        <div class="col-md-10">

        	<g:layoutBody/>
          
        </div>

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

		<r:layoutResources />
	</body>
</html>
