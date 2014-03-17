databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1395089560189-1") {
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

	changeSet(author: "julien (generated)", id: "1395089560189-2") {
		addColumn(tableName: "commande") {
			column(name: "frais_port", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1395089560189-3") {
		dropColumn(columnName: "frais_port", tableName: "ligne_commande")
	}
}
