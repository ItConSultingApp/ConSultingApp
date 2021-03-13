package com.example.it_consultiong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.it_consultiong.databinding.ActivityChooseMajorBinding

class MajorChooseActivity : AppCompatActivity() {
    private val binding by lazy { ActivityChooseMajorBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
    }




}