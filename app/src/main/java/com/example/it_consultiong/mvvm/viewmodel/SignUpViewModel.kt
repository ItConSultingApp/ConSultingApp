package com.example.it_consultiong.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.it_consultiong.Room.database.SignUpDatabase
import com.example.it_consultiong.mvvm.models.SignUpData
import com.example.it_consultiong.mvvm.repository.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


@InternalCoroutinesApi
class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val signUpDao = SignUpDatabase.getDatabase(
        application
    ).signUpDao()


    private val repository: SignUpRepository = SignUpRepository(signUpDao)
    val getAllData: LiveData<List<SignUpData>> = repository.getAllData
    val signInData: LiveData<List<SignUpData>> = repository.signInData


    fun insertData(signUpData: SignUpData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(signUpData)
        }
    }
}