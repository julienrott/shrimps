databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1391960680081-1") {
		createTable(tableName: "photo") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "photoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "data", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "data_slider", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "data_small", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "data_small_homepage", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "produit_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "titre", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1391960680081-2") {
		createTable(tableName: "produit") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "produitPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "categorie_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "prix", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "titre", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1391960680081-5") {
		createIndex(indexName: "FK65B3E323D768F06", tableName: "photo") {
			column(name: "produit_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1391960680081-6") {
		createIndex(indexName: "FKED8DCDA9F4C77146", tableName: "produit") {
			column(name: "categorie_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1391960680081-3") {
		addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "photo", constraintName: "FK65B3E323D768F06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1391960680081-4") {
		addForeignKeyConstraint(baseColumnNames: "categorie_id", baseTableName: "produit", constraintName: "FKED8DCDA9F4C77146", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "categorie", referencesUniqueColumn: "false")
	}
}
