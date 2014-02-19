databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1392488718136-1") {
		createTable(tableName: "adresse") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "adressePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "code_postal", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ligne1", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ligne2", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ville", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-2") {
		createTable(tableName: "commande") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "commandePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "client_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime")
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-3") {
		createTable(tableName: "ligne_commande") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ligne_commandPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "commande_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-4") {
		addColumn(tableName: "user") {
			column(name: "adresse_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-8") {
		createIndex(indexName: "FKDC160A7AB60F8F6E", tableName: "commande") {
			column(name: "client_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-9") {
		createIndex(indexName: "FK7281EF3880DA7B0E", tableName: "ligne_commande") {
			column(name: "commande_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-10") {
		createIndex(indexName: "FK36EBCB9DA6946", tableName: "user") {
			column(name: "adresse_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1392488718136-5") {
		addForeignKeyConstraint(baseColumnNames: "client_id", baseTableName: "commande", constraintName: "FKDC160A7AB60F8F6E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1392488718136-6") {
		addForeignKeyConstraint(baseColumnNames: "commande_id", baseTableName: "ligne_commande", constraintName: "FK7281EF3880DA7B0E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "commande", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1392488718136-7") {
		addForeignKeyConstraint(baseColumnNames: "adresse_id", baseTableName: "user", constraintName: "FK36EBCB9DA6946", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "adresse", referencesUniqueColumn: "false")
	}
}
