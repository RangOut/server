package br.com.rangout

class Restaurant {

	String name
	String address
	String phoneNumber

	//TODO add tables

	static belongsTo = [owner: Admin]
    static constraints = {
    }
}
