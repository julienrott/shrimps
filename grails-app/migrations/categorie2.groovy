databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1391885198140-1") {
		addColumn(tableName: "categorie") {
			column(name: "position", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1391885198140-2") {
		addColumn(tableName: "categorie") {
			column(name: "titre", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
