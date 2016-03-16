package com.herokuapp.rangout

class User {

    String oAuth

    static constraints = {
        oAuth unique: true, nullable: false
    }
}
