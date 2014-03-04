databaseChangeLog = {

	changeSet(author: "julien (generated)", id: "1393973294856-1") {
		addColumn(tableName: "home_page_slider") {
			column(name: "accueil", type: "longtext")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-2") {
		addColumn(tableName: "home_page_slider") {
			column(name: "top1_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-3") {
		addColumn(tableName: "home_page_slider") {
			column(name: "top2_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-4") {
		addColumn(tableName: "home_page_slider") {
			column(name: "top3_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-5") {
		addColumn(tableName: "home_page_slider") {
			column(name: "top4_id", type: "bigint")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-10") {
		createIndex(indexName: "FK7C83E13190A3FE53", tableName: "home_page_slider") {
			column(name: "top1_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-11") {
		createIndex(indexName: "FK7C83E13190A472B2", tableName: "home_page_slider") {
			column(name: "top2_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-12") {
		createIndex(indexName: "FK7C83E13190A4E711", tableName: "home_page_slider") {
			column(name: "top3_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-13") {
		createIndex(indexName: "FK7C83E13190A55B70", tableName: "home_page_slider") {
			column(name: "top4_id")
		}
	}

	changeSet(author: "julien (generated)", id: "1393973294856-6") {
		addForeignKeyConstraint(baseColumnNames: "top1_id", baseTableName: "home_page_slider", constraintName: "FK7C83E13190A3FE53", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1393973294856-7") {
		addForeignKeyConstraint(baseColumnNames: "top2_id", baseTableName: "home_page_slider", constraintName: "FK7C83E13190A472B2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1393973294856-8") {
		addForeignKeyConstraint(baseColumnNames: "top3_id", baseTableName: "home_page_slider", constraintName: "FK7C83E13190A4E711", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}

	changeSet(author: "julien (generated)", id: "1393973294856-9") {
		addForeignKeyConstraint(baseColumnNames: "top4_id", baseTableName: "home_page_slider", constraintName: "FK7C83E13190A55B70", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "produit", referencesUniqueColumn: "false")
	}
}
