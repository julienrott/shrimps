<%@page defaultCodec="none" %>
<%@ page contentType="text/html"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<div>
		<g:link uri="/" absolute="true">
           	<r:img dir="images" file="logo.jpg" width="120" absolute="true" base="https://www.shrimpsforall.fr"/>
       	</g:link>
       	
		<g:link uri="/" absolute="true">
	       	<r:img dir="images" file="baniere_plate.png" height="70" absolute="true" base="https://www.shrimpsforall.fr"/>
       	</g:link>
	</div>
	
	<div>
		<p>&nbsp;</p>
	</div>
	
	<div>
		${msg.decodeHTML()}
	</div>
	
</body>
</html>