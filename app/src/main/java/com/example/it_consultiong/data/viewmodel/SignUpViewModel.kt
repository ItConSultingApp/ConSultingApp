package com.example.it_consultiong.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.it_consultiong.data.SignUpDatabase
import com.example.it_consultiong.data.models.SignUpData
import com.example.it_consultiong.data.repository.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


@InternalCoroutinesApi
open class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val signUpDao = SignUpDatabase.getDatabase(
        application
    ).signUpDao()

    private val repository: SignUpRepository = SignUpRepository(signUpDao)
    val getAllData: LiveData<List<SignUpData>> = repository.getAllData
    val getSignUpData: LiveData<List<SignUpData>> = repository.getSignUpData




    fun insertData(signUpData: SignUpData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(signUpData)
        }
    }
}