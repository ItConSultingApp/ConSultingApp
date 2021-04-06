package com.cotion.it_consultiong.mvvm.viewmodel

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.Main.Splash
import com.cotion.it_consultiong.UI.Main.Splash.Companion.auth
import com.cotion.it_consultiong.UI.Main.Splash.Companion.uid
import com.cotion.it_consultiong.UI.Sign.Dialog.App
import com.cotion.it_consultiong.data.data_model.signInUserInfo
import com.cotion.it_consultiong.mvvm.models.SignUpData
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.InternalCoroutinesApi

@Suppress("UNREACHABLE_CODE")
class ShareViewModel(application: Application) : AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    //데이터 있음
    fun checkIfDatabaseEmpty(signUpData: List<SignUpData>) {
        emptyDatabase.value = signUpData.isEmpty()
    }

    fun checkIfTextNull(text: String): Boolean{
        return !TextUtils.isEmpty(text.trim {it <=' '})
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
    fun getUserInfo(){
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.child("users").child(uid).getValue(signInUserInfo::class.java)
                Log.d("증명","post name : ${post?.userName}")
                Splash.userName = post?.userName
                Splash.userGrade = post?.userGrade
                Splash.userClass = post?.userClass
                Splash.userNumber = post?.userNumber
                Splash.userEmail = post?.userEmail
                Splash.userPassword = post?.userPassword
                Splash.userJob = post?.userJob
                Log.d("리그", post?.userClass.toString())
                Log.d("증명","onDataChange 여기야")

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("증명","$databaseError")
            }
        }
        Splash.database.addListenerForSingleValueEvent (postListener)

    }



    @InternalCoroutinesApi
    fun startGetUserInfo(){
        val auth = FirebaseAuth.getInstance()
//        Toast.makeText(App.instance,"${auth.currentUser?.uid}",Toast.LENGTH_SHORT).show()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = auth.currentUser?.uid?.let { dataSnapshot.child("users").child(it).getValue(signInUserInfo::class.java) }
                Splash.userName = post?.userName
                Splash.userGrade = post?.userGrade
                Splash.userClass = post?.userClass
                Splash.userNumber = post?.userNumber
                Splash.userEmail = post?.userEmail
                Splash.userPassword = post?.userPassword
                Splash.userJob = post?.userJob
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        Splash.database.addListenerForSingleValueEvent (postListener)

    }
}