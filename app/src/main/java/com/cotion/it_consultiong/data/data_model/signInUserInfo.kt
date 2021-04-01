package com.cotion.it_consultiong.data.data_model

import android.net.Uri

class signInUserInfo (
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userGrade: String?,
    var userClass: String?,
    var userNumber: String?,
    var userJob: String?,
    var userProfile: Uri?
){
    constructor(): this(null,null,null,null,null,null,null,null){

    }
}
//    constructor() {
//    }


//    constructor(userName : String?, userEmail : String?,
//                userPassword : String?, userGrade : String?,
//    userClass:String?, userNumber:String?, userJob : String?, userProfile: Uri?) {
//        this.userName = userName
//        this.userEmail = userEmail
//        this.userPassword = userPassword
//        this.userGrade = userGrade
//        this.userClass = userClass
//        this.userNumber = userNumber
//        this.userJob = userJob
//        this.userProfile = userProfile
//    }
