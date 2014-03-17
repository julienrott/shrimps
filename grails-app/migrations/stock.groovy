databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1394903536691-1") {
		createTable(tableName: "stock") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "stockPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "quantite", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1394903536691-2") {
		addColumn(tableName: "produit") {
			column(name: "stock", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
