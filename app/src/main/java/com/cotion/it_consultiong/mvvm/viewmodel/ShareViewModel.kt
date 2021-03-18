package com.cotion.it_consultiong.mvvm.viewmodel

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cotion.it_consultiong.mvvm.models.SignUpData

@Suppress("UNREACHABLE_CODE")
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
        major: String
    ): Boolean {
        // TextUtils 이란, Android 에서 제공하는 TextView null 체크 방식이다.
        return if (TextUtils.isEmpty(name) || TextUtils.isEmpty(id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                grade
            ) || TextUtils.isEmpty(signCalss) || TextUtils.isEmpty(classNumber) || TextUtils.isEmpty(
                major
            )
        ) {
            false
        } else
            !(TextUtils.isEmpty(name) || TextUtils.isEmpty(id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                grade
            ) || TextUtils.isEmpty(signCalss) || TextUtils.isEmpty(classNumber)) || TextUtils.isEmpty(
                major
            )

    }

    fun signNullTest(id: String, pwd: String): Boolean {
        return if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pwd))
            return false
        else {
            !(TextUtils.isEmpty(id) || TextUtils.isEmpty(pwd))

        }

    }


}
