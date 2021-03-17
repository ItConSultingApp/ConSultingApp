package com.example.it_consultiong.mvvm.repository

import androidx.lifecycle.LiveData
import com.cotion.it_consultiong.Dao.SignUpDao
import com.cotion.it_consultiong.mvvm.models.SignUpData


class SignUpRepository(private val signUpDao: SignUpDao) {
    val getAllData: LiveData<List<com.cotion.it_consultiong.mvvm.models.SignUpData>> = signUpDao.getAllData()

    suspend fun insertData(signUpData: SignUpData) {
        signUpDao.insertData(signUpData)
    }


}