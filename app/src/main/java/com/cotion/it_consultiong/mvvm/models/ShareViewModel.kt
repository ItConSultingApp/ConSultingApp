package com.cotion.it_consultiong.mvvm.models

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cotion.it_consultiong.data.data_model.signInUserInfo
import com.cotion.it_consultiong.ui.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.InternalCoroutinesApi

@Suppress("UNREACHABLE_CODE")
class ShareViewModel(application: Application) : AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    //데이터 있음

    fun checkIfTextNull(text: String): Boolean {
        return !TextUtils.isEmpty(text.trim { it <= ' ' })
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


    @InternalCoroutinesApi
    fun startGetUserInfo() {
        val auth = FirebaseAuth.getInstance()
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        Log.d("Mylog", "my uid : ${auth.currentUser.uid}")

        database = FirebaseDatabase.getInstance().reference
        database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val modelData = auth.currentUser?.uid?.let {
                    snapshot.child(it).getValue(
                        signInUserInfo::class.java
                    )
                }

                SplashActivity.userName = modelData?.userName
                SplashActivity.userGrade = modelData?.userGrade
                SplashActivity.userClass = modelData?.userClass
                SplashActivity.userNumber = modelData?.userNumber
                SplashActivity.userEmail = modelData?.userEmail
                SplashActivity.userPassword = modelData?.userPassword
                SplashActivity.userJob = modelData?.userJob

                Log.d("Mylog", "modelData name : ${modelData?.userName}")

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }


}