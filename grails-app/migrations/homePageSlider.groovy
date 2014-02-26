databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1393363331617-1") {
		createTable(tableName: "home_page_slider") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "home_page_sliPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1393363331617-2") {
		createTable(tableName: "photo_home_page") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "photo_home_paPK")
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

			column(name: "description", type: "varchar(255)")

			column(name: "home_page_slider_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "position", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "titre", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1393363331617-4") {
		createIndex(indexName: "FKAE10FC224BDEF20C", tableName: "photo_home_page") {
			column(name: "home_page_slider_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1393363331617-3") {
		addForeignKeyConstraint(baseColumnNames: "home_page_slider_id", baseTableName: "photo_home_page", constraintName: "FKAE10FC224BDEF20C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "home_page_slider", referencesUniqueColumn: "false")
	}
}
