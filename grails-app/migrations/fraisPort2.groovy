databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1394053021835-1") {
		createTable(tableName: "frais_port") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "frais_portPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "titre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(5)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1394053021835-2") {
		createTable(tableName: "plage_frais_port") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "plage_frais_pPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "debut", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fin", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "frais_port_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1394053021835-3") {
		addColumn(tableName: "produit") {
			column(name: "frais_port_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1394053021835-4") {
		addColumn(tableName: "produit") {
			column(name: "poids", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1394053021835-7") {
		createIndex(indexName: "FKA240EBFDBB581B65", tableName: "plage_frais_port") {
			column(name: "frais_port_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1394053021835-8") {
		createIndex(indexName: "FKED8DCDA9BB581B65", tableName: "produit") {
			column(name: "frais_port_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1394053021835-9") {
		dropColumn(columnName: "frais_port", tableName: "produit")
	}

	changeSet(author: "julien (generated)", id: "1394053021835-5") {
		addForeignKeyConstraint(baseColumnNames: "frais_port_id", baseTableName: "plage_frais_port", constraintName: "FKA240EBFDBB581B65", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "frais_port", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1394053021835-6") {
		addForeignKeyConstraint(baseColumnNames: "frais_port_id", baseTableName: "produit", constraintName: "FKED8DCDA9BB581B65", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "frais_port", referencesUniqueColumn: "false")
	}
}
