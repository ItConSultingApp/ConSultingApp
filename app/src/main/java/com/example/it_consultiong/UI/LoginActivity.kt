package com.example.it_consultiong

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.it_consultiong.UI.SignUpActivity
import com.example.it_consultiong.mvvm.viewmodel.ObjectClass

import com.example.it_consultiong.databinding.ActivityLoginBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class LoginActivity() : AppCompatActivity() {


    private val TAG = "LoginActivity"
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    val objectClass = ObjectClass()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUp.setOnClickListener() {
            Log.d(TAG, "LoginActivity - signUp() called")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun SignInLogin() {

    }


}


//1. DB에 있는 id와 password를 불러와 회원가입할 때 적었던 id와 password를 비교하여 맞으면 true 아니면 x
//회원가입 할 때 로그인과 비밀번호를 여깄다가 보냄 , 이걸 Insert? ???
// db에 있는 id와 비밀번호를 꺼내는 방법 한 테이블이겠지 ??






