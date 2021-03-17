package com.cotion.it_consultiong.mvvm.viewmodel

data class SignUpUserModel(
    var userName : String,
    var userEmail : String,
    var userPassword : String,
    var userGrade : String,
    var userClass : String,
    var userNumber : String,
    var userJob : String
) {
    constructor(): this("","","","1","1","1","")
}