databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1395441053911-1") {
		addColumn(tableName: "photo") {
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}
}
