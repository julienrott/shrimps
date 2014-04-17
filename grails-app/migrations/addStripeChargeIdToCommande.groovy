databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1397632219056-1") {
		addColumn(tableName: "commande") {
			column(name: "stripe_charge_id", type: "varchar(255)")
		}
	}
}
