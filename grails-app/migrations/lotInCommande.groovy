databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1395088149278-1") {
		addColumn(tableName: "ligne_commande") {
			column(name: "lot_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1395088149278-3") {
		createIndex(indexName: "FK7281EF38F170A406", tableName: "ligne_commande") {
			column(name: "lot_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1395088149278-2") {
		addForeignKeyConstraint(baseColumnNames: "lot_id", baseTableName: "ligne_commande", constraintName: "FK7281EF38F170A406", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lot", referencesUniqueColumn: "false")
	}
}
