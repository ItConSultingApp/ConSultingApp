package com.example.it_consultiong.data

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromSignIn(value : SignIn): String{
        return value.name
    }
    @TypeConverter
    fun  toPriority(value:String): SignIn {
        return SignIn.valueOf(value)
    }
}