package com.cotion.it_consultiong.UI.Main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.FragmentMainActivity
import com.cotion.it_consultiong.UI.LoginMainActivity
import com.cotion.it_consultiong.UI.LoginMainActivity.Companion.AUTHTAG
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userName
import com.cotion.it_consultiong.data.data_model.signInUserInfo
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.InternalCoroutinesApi

class Splash : AppCompatActivity() {

    companion object {
        lateinit var auth: FirebaseAuth
        val database = FirebaseDatabase.getInstance().reference
        lateinit var uid: String

        //로그인된 유저 정보
        var userName: String? = null
        var userEmail: String? = null
        var userPassword: String? = null
        var userGrade: String? = null
        var userClass: String? = null
        var userNumber: String? = null
        var userJob: String? = null
        var userProfile: Uri? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }


    //이게 있어야 LoginMainActivity로 intent가 되는데 뭔지는 모르겠음
    @InternalCoroutinesApi
    override fun onStart() {
        super.onStart()
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        Log.d("증명", "$auth")
        if (auth.currentUser == null) {
            Handler().postDelayed(
                {
                    goNext(false)
                },
                1500
            )

        } else {
            val shareViewModel = ShareViewModel(application)
            shareViewModel.startGetUserInfo()
            Log.d("aa", "입력받은 유저 name : $userName")

            Handler().postDelayed(
                {
                    val intent = Intent(this, LoginMainActivity::class.java)
                    startActivity(intent)
                },
                2500
            )
        }

    }


    @InternalCoroutinesApi
    private fun goNext(new: Boolean) {
        if (new) {
            val intent = Intent(this, FragmentMainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginMainActivity::class.java)
            startActivity(intent)
        }

    }

}