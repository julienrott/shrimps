databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1399111426524-1") {
		createTable(tableName: "config") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "configPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "mail_host", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "mail_password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "mail_port", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "mail_properties", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "mail_username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
