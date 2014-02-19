databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1392567154721-1") {
		addColumn(tableName: "user") {
			column(name: "nom", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392567154721-2") {
		addColumn(tableName: "user") {
			column(name: "prenom", type: "varchar(255)")
		}
	}
}
