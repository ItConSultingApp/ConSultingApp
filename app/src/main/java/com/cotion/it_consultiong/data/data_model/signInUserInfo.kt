package com.cotion.it_consultiong.data.data_model

data class signInUserInfo(
    val name: String?,
    val grade:String?,
    val grad_class : String?,
    val class_number:String?,
    val job : String?,
    val profileImg : String?
){
    constructor(): this(null,null,null,null,null,null){

    }

}