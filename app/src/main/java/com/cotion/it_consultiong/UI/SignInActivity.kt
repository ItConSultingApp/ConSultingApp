package com.cotion.it_consultiong.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import com.cotion.it_consultiong.mvvm.viewmodel.SignUpViewModel

import com.cotion.it_consultiong.databinding.ActivityEmailSignInBinding
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmailSignInBinding
    private val TAG = "SignInActivity"
    private val mSharedViewModel: ShareViewModel by viewModels()
    private val objectClass = ObjectClass()
    private val mSignUpViewModel: SignUpViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.setToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "로그인"


        binding.signInBtn.setOnClickListener {


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





}