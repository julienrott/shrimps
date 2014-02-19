databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1392569230108-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "ligne2", tableName: "adresse")
	}
}
