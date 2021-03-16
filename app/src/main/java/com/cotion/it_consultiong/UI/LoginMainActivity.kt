package com.cotion.it_consultiong.UI

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.databinding.ActivityLoginMainBinding
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class LoginMainActivity() : AppCompatActivity() {


    private val TAG = "LoginActivity"
    private val binding by lazy { ActivityLoginMainBinding.inflate(layoutInflater) }

    val objectClass = ObjectClass()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUp.setOnClickListener() {
            Log.d(TAG, "LoginActivity - signUp() called")
            val intent = Intent(this, SignUpActivity::class.java)
            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(binding.signUp,"signUp"),
                Pair.create(binding.appName1,"logoTransition")

                )

            startActivity(intent,options.toBundle())

        }

        binding.signIn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(binding.appName1,"logoTransition"),
                Pair.create(binding.signIn,"emailLoginTransition"),

            )
            startActivity(intent,options.toBundle())
        }


        binding.signInGoogle.setOnClickListener {

        }
    }




}


//1. DB에 있는 id와 password를 불러와 회원가입할 때 적었던 id와 password를 비교하여 맞으면 true 아니면 x
//회원가입 할 때 로그인과 비밀번호를 여깄다가 보냄 , 이걸 Insert? ???
// db에 있는 id와 비밀번호를 꺼내는 방법 한 테이블이겠지 ??