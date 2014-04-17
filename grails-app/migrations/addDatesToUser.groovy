databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1397632425545-2") {
		addColumn(tableName: "user") {
			column(name: "date_created", type: "datetime")
		}
	}

	changeSet(author: "julien (generated)", id: "1397632425545-3") {
		addColumn(tableName: "user") {
			column(name: "last_updated", type: "datetime")
		}
	}
}
