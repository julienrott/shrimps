<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>
    
  <div class="jumbotron">

    <div class="container">
      <h2>Configuration</h2>

      <g:form name="form" action="save" role="form">
      	<g:hiddenField name="id" value="${config?.id}"/>
      	<g:textField name="mailHost" placeholder="mailHost" value="${config?.mailHost}"/>
      	<g:textField name="mailPort" placeholder="mailPort" value="${config?.mailPort}"/>
      	<g:textField name="mailUsername" placeholder="mailUsername" value="${config?.mailUsername}"/>
      	<g:passwordField name="mailPassword" placeholder="mailPassword" value="${config?.mailPassword}"/>
      	<g:textField name="mailProperties" placeholder="mailProperties" value="${config?.mailProperties}"/>
      	<g:submitButton name="formBtn" value="Enregistrer" class="btn btn-primary"/>
      	<g:if test="${flash.message}">
      		<div class="alert alert-danger">${flash.message}</div>
      	</g:if>
      </g:form>
      
      <button class="btn btn-primary" id="sendMailTest">Test envoi de mail</button>
      <g:javascript>
      	$("#sendMailTest").on("click", {}, function(){
      		$.get("${createLink(controller: 'config', action: 'sendMailTest')}", function(data){alert(data);});
      	});
      </g:javascript>

    </div>

  </div>
  
</body>
</html>