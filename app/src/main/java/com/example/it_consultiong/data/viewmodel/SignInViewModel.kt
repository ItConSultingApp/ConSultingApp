package com.example.it_consultiong.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.it_consultiong.Dao.SignInDao
import com.example.it_consultiong.data.models.SignInData
import com.example.it_consultiong.data.repository.SignInRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = SignInDatabase.getDatabase(
        application
    ).signInDao()

    private val repository: SignInRepository = SignInRepository(toDoDao)
//    val getAllData: LiveData<List<SignInDao>> = repository.getAllData


    fun SignInsertData(signInData: SignInData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(signInData)
        }
    }
}