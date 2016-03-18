package br.com.rangout

/**
* Class to define the end user of the system. 
* The one who will make orders in a Restaurant, see plates. But DOESN'T 
* affect how restaurants work and shouldn't be allowed under any circumstances into 
* the management area.
**/
class User {

	String email
	String password
	String facebookAccessToken


    static constraints = {
    }
}
