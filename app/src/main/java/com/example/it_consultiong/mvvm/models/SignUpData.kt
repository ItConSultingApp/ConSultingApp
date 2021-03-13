package com.example.it_consultiong.mvvm.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "sign_up")
data class SignUpData(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var signName: String,
        var signId: String,
        var signPwd: String,
        var signGrade: String,
        var signclass: String,
        var signclassnumber: String
)