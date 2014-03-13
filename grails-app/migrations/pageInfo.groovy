databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1394735155557-1") {
		createTable(tableName: "page_info") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "page_infoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "contenu", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "position", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "titre", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
