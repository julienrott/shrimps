databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1396559613774-1") {
		addColumn(tableName: "commande") {
			column(name: "paypal_transaction_id", type: "varchar(255)")
		}
	}
}
