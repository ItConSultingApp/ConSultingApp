package com.example.it_consultiong.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.it_consultiong.databinding.ActivityEmailSignInBinding


class SignInActivity : AppCompatActivity() {
    lateinit var binding:ActivityEmailSignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.userIdEdittxt.text!!.isEmpty()){
            binding.userIdLayout.error = "아이디를 입력해주세요"
        }else{
            binding.userIdLayout.error = null
        }
    }
}