package com.example.it_consultiong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.it_consultiong.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
   private val binding by lazy{ActivitySignUpBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}