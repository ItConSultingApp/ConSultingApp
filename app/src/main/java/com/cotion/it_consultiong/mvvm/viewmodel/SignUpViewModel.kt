package com.cotion.it_consultiong.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cotion.it_consultiong.Room.database.SignUpDatabase
import com.cotion.it_consultiong.mvvm.models.SignUpData
import com.cotion.it_consultiong.mvvm.repository.BoardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


@InternalCoroutinesApi
class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val signUpDao = SignUpDatabase.getDatabase(
        application
    ).signUpDao()


//    private val repository: BoardRepository = BoardRepository(signUpDao)
 //   val getAllData: LiveData<List<SignUpData>> = repository.getAllData



 //   fun insertData(signUpData: SignUpData) {
   //     viewModelScope.launch(Dispatchers.IO) {
     //       repository.insertData(signUpData)
       // }
   //    }
}