package com.example.it_consultiong.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.cotion.it_consultiong.databinding.ActivityEmailSignInBinding
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel



class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmailSignInBinding

    private val mSharedViewModel: ShareViewModel by viewModels()
    private val objectClass = ObjectClass()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.setToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "로그인"


        binding.signInBtn.setOnClickListener {
            signIn()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun signIn() {
        // editText id값
        val id = binding.signInId.text.toString()
        // editText pwd 값
        val pwd = binding.signInPwd.text.toString()

        // signUp 의 id값
        val inId = intent.getStringExtra("id")
        // signUp 의 pwd값
        val inPwd = intent.getStringExtra("pwd")

        // null Test
        val signNullTest = mSharedViewModel.signNullTest(id, pwd)

        if (signNullTest) {

                objectClass.showToast(this, "로그인 성공")
            }
            objectClass.showToast(this, "빈칸을 입력해 주세요")
        }




}