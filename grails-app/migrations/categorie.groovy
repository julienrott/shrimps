databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1391875656819-1") {
		createTable(tableName: "categorie") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categoriePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
}
