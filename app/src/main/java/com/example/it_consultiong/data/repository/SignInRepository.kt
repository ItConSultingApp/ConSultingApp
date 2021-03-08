package com.example.it_consultiong.data.repository

import androidx.lifecycle.LiveData
import com.example.it_consultiong.Dao.SignInDao
import com.example.it_consultiong.data.models.SignInData

class SignInRepository(private val signInDao: SignInDao) {
//    val getAllData: LiveData<List<SignInDao>> = signInDao.getAllSiginData()

    suspend fun insertData(signInData : SignInData) {
        signInDao.signinsertData(signInData)
    }
}