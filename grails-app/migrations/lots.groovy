databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1393533604078-1") {
		createTable(tableName: "lot") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "lotPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "prix", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "produit_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "titre", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1393533604078-3") {
		createIndex(indexName: "FK1A3513D768F06", tableName: "lot") {
			column(name: "produit_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1393533604078-2") {
		addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "lot", constraintName: "FK1A3513D768F06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}
}
