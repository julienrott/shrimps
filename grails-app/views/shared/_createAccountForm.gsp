<h2>Je crée mon compte</h2>

<g:if test="${message}">
	<div class="alert alert-danger">${message}</div>
</g:if>

<g:hasErrors bean="${user}">
	<ul class="alert alert-danger">
		<g:eachError var="err" bean="${user}">
	    	<li><g:message error="${err}" /></li>
		</g:eachError>
	</ul>
</g:hasErrors>

	<g:form name="createAccountForm" autocomplete="off">

		<div class="form-group ${hasErrors(bean:user,field:'username','alert alert-danger')}">
    	<label for="j_username2">Email</label>
    	<g:textField name="j_username2" placeholder="Email" class="form-control" value="${user?.username}"/>
	</div>

		<div class="form-group">
    	<label for="j_username2_confirm">Confirmer Email</label>
		<g:textField name="j_username2_confirm" placeholder="Confirmer Email" class="form-control"/>
	</div>

	<div class="form-group ${hasErrors(bean:user,field:'password','alert alert-danger')}">
    	<label for="j_password2">Mot de passe</label>
    	<g:passwordField name="j_password2" placeholder="Mot de passe" class="form-control"/>
	</div>

	<div class="form-group">
    	<label for="j_password2_confirm">Confirmer mot de passe</label>
    	<g:passwordField name="j_password2_confirm" placeholder="Confirmer mot de passe" class="form-control"/>
	</div>

    <g:submitButton name="loginbtn" value="Créer mon compte" event="createAccount" class="btn btn-primary"/>
</g:form>