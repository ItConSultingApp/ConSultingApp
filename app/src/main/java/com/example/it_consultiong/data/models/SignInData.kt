package com.example.it_consultiong.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="sign_in")
data class SignInData(
    @PrimaryKey(autoGenerate = true)
    var Sid:Long,
    var SignInId: String,
    var pwd: String
)