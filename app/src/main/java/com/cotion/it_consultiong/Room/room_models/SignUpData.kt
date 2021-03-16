package com.cotion.it_consultiong.Room.room_models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "sign_up")
data class SignUpData(
        @PrimaryKey(autoGenerate = true)
        @Ignore var id: String,
        var signId: String,
        var signpwd: String,
        var signName: String,
        var signGrade: String,
        var signclass: String,
        var signclassnumber: String,

        )