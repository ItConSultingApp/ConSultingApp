package com.example.it_consultiong.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sign_up")
data class SignUpData(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var signId: String,
    var signpwd: String,
    var signName: String,
    var signGrade: String,
    var signclass: String,
    var signclassnumber: String,
    var signmajor: String


)