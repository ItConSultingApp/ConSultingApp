package com.example.it_consultiong.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.it_consultiong.data.models.SignUpData
import com.google.firebase.firestore.auth.User

@Dao
interface SignUpDao {


    @Query("SELECT * FROM sign_up  ")
    fun getAllData(signId: String, signPwd:String): Array<SignUpData>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // PrimaryKey 가 겹치면 무시한다.
    suspend fun insertData(signupData: SignUpData)

    @Delete
    suspend fun deleteData(signupData: SignUpData)

    @Update
    suspend fun updateData(signupData: SignUpData)


}


