<!DOCTYPE html>
<html lang="en">
<head>
  <meta content="main" name="layout">
</head>
<body>

  <div class="row">
    <h2>${titre}</h2>

    <table class="table table-striped">
      <thead>
        <tr>
          <th>date</th>
          <sec:ifAllGranted roles="ROLE_ADMIN">
            <th>Client</th>
          </sec:ifAllGranted>
          <th>Montant</th>
          <th>Statut</th>
          <th></th>
        </tr>
      </thead>

      <tbody>
        <g:each in="${commandes}">
          <tr>
            <td><g:link action="details" id="${it.id}">${formatDate(date: it.dateCreated, format: "dd/MM/yyyy")}</g:link></td>
            
            <sec:ifAllGranted roles="ROLE_ADMIN">
              <td>
                <g:link action="details" id="${it.id}">${it.client.nom} ${it.client.prenom}</g:link>
              </td>
            </sec:ifAllGranted>
            
            <td class="text-right"><g:link action="details" id="${it.id}">${formatNumber(number: it?.totaux()?.totalTTC, type: 'currency', currencyCode: 'EUR')}</g:link></td>
            
            <td>
              <g:set var="label" value="${it.statut == "payée" ? "primary" : it.statut == "expédiée" ? "success" : it.statut == "création" ? "warning" : ""}"/>
              <g:link action="details" id="${it.id}" class="label label-${label}">
                ${it.statut}
              </g:link>
            </td>
            
            <sec:ifAllGranted roles="ROLE_ADMIN">
              <td class="col-md-5"></td>
            </sec:ifAllGranted>
            <sec:ifNotGranted roles="ROLE_ADMIN">
              <td class="col-md-8"></td>
            </sec:ifNotGranted>
            
          </tr>
        </g:each>
      </tbody>

    </table>

  </div>
</body>
</html>