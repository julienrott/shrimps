package fr.shrimpsforall

import java.io.Serializable;

class Config implements Serializable {
	
	String mailHost
	int mailPort
	String mailUsername
	String mailPassword
	String mailProperties

    static constraints = {
    }
}
