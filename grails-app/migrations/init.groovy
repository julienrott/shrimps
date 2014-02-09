databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1391856571028-1") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-2") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-3") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-4") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "julien (generated)", id: "1391856571028-7") {
		createIndex(indexName: "authority_uniq_1391856570956", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-8") {
		createIndex(indexName: "username_uniq_1391856570965", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-9") {
		createIndex(indexName: "FK143BF46A1EC8976E", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-10") {
		createIndex(indexName: "FK143BF46A799DD38E", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1391856571028-5") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A799DD38E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1391856571028-6") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A1EC8976E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
