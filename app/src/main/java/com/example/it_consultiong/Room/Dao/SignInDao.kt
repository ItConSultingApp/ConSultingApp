package com.example.it_consultiong.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.it_consultiong.data.models.SignInData

@Dao
interface SignInDao {
//    @Query("SELECT * FROM  sign_in")
//    fun getAllSiginData(): LiveData<List<SignInDao>>

    @Insert
    suspend fun signinsertData(signInData: SignInData)

}