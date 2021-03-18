package com.cotion.it_consultiong.mvvm.repository

import androidx.lifecycle.LiveData
<<<<<<<<< Temporary merge branch 1:app/src/main/java/com/cotion/it_consultiong/mvvm/repository/SignUpRepository.kt
import com.cotion.it_consultiong.Dao.SignUpDao
import com.cotion.it_consultiong.mvvm.models.SignUpData
=========
import com.example.it_consultiong.Room.Dao.SignUpDao
import com.example.it_consultiong.mvvm.models.SignUpData
>>>>>>>>> Temporary merge branch 2:app/src/main/java/com/example/it_consultiong/mvvm/repository/SignUpRepository.kt


class SignUpRepository(private val signUpDao: SignUpDao) {
    val getAllData: LiveData<List<SignUpData>> = signUpDao.getAllData()






    suspend fun insertData(signUpData: SignUpData) {
        signUpDao.insertData(signUpData)
    }


}