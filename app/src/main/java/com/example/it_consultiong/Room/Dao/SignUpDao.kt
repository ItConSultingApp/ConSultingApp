package com.example.it_consultiong.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.it_consultiong.data.models.SignUpData

@Dao
interface SignUpDao {

     //todo_table 에 있는 id를 오름차순 정렬해라
     @Query("SELECT * FROM  sign_up")
    fun getAllData(): LiveData<List<SignUpData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // PrimaryKey 가 겹치면 무시한다.
    suspend fun insertData(signupData: SignUpData)

    @Delete
    suspend fun deleteData(signupData: SignUpData)

    @Update
    suspend fun updateData(signupData: SignUpData)






}