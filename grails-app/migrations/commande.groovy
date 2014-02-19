databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1392651074682-1") {
		addColumn(tableName: "commande") {
			column(name: "adresse", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-2") {
		addColumn(tableName: "commande") {
			column(name: "code_postal", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-3") {
		addColumn(tableName: "commande") {
			column(name: "compl_adresse", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-4") {
		addColumn(tableName: "commande") {
			column(name: "nom", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-5") {
		addColumn(tableName: "commande") {
			column(name: "prenom", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-6") {
		addColumn(tableName: "commande") {
			column(name: "statut", type: "varchar(8)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-7") {
		addColumn(tableName: "commande") {
			column(name: "ville", type: "varchar(255)")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-8") {
		addColumn(tableName: "ligne_commande") {
			column(name: "frais_port", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-9") {
		addColumn(tableName: "ligne_commande") {
			column(name: "prix", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-10") {
		addColumn(tableName: "ligne_commande") {
			column(name: "produit_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-11") {
		addColumn(tableName: "ligne_commande") {
			column(name: "quantite", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-12") {
		addColumn(tableName: "ligne_commande") {
			column(name: "titre", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-14") {
		createIndex(indexName: "FK7281EF383D768F06", tableName: "ligne_commande") {
			column(name: "produit_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1392651074682-13") {
		addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "ligne_commande", constraintName: "FK7281EF383D768F06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}
}
