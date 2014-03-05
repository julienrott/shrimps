databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1394058154260-1") {
		addColumn(tableName: "plage_frais_port") {
			column(name: "prix", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
}
