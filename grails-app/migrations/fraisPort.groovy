databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1392370918166-1") {
		addColumn(tableName: "produit") {
			column(name: "frais_port", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
}
