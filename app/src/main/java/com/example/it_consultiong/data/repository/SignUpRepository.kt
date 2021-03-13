package com.example.it_consultiong.data.repository

import androidx.lifecycle.LiveData
import com.example.it_consultiong.Dao.SignUpDao
import com.example.it_consultiong.mvvm.models.SignUpData


class SignUpRepository(private val signUpDao: SignUpDao) {
    val getAllData: LiveData<List<SignUpData>> = signUpDao.getAllData()

    suspend fun insertData(signUpData: SignUpData) {
        signUpDao.insertData(signUpData)
    }


}