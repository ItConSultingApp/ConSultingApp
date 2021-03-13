package com.example.it_consultiong.mvvm.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.it_consultiong.mvvm.models.SignUpData

class ShareViewModel(application: Application) : AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    //데이터 있음
    fun checkIfDatabaseEmpty(signUpData: List<SignUpData>) {
        emptyDatabase.value = signUpData.isEmpty()
    }

    fun verifyDtaFromUser(
            name: String,
            id: String,
            password: String,
            grade: String,
            signCalss: String,
            classNumber: String,
            ): Boolean {
        // TextUtils 이란, Android 에서 제공하는 TextView null 체크 방식이다.
        return if (TextUtils.isEmpty(name) || TextUtils.isEmpty(id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                        grade
                ) || TextUtils.isEmpty(signCalss) || TextUtils.isEmpty(classNumber)
        ) {
            false
        } else
            !(TextUtils.isEmpty(name) || TextUtils.isEmpty(id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                    grade
            ) || TextUtils.isEmpty(signCalss) || TextUtils.isEmpty(classNumber))

    }






}

