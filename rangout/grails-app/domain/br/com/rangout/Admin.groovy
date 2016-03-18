package br.com.rangout

class Admin {

	String email
	String name
	String password

	static hasOne = [restaurant: Restaurant]

    static constraints = {
    	email unique: true
    	name blank:true
    	password password:true
    }
}
