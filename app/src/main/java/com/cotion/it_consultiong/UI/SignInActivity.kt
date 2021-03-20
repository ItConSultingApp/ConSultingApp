package com.cotion.it_consultiong.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.mvvm.viewmodel.SignUpViewModel

import com.cotion.it_consultiong.databinding.ActivityEmailSignInBinding
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmailSignInBinding
    private val TAG = "SignInActivity"
    private val mSharedViewModel: ShareViewModel by viewModels()
    private val objectClass = ObjectClass()
    private val mSignUpViewModel: SignUpViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        setSupportActionBar(binding.setToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "로그인"


        binding.signInBtn.setOnClickListener {
            var signinEmailTxt = binding.signInId.text.toString().trim()
            var signinPasswordTxt = binding.signInPwd.text.toString().trim()

            if (!signinEmailTxt.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$"))) {
                toastOrEgg(
                    "올바른 이메일 형식이 아닙니다",
                    0,
                    R.color.black,
                    R.color.white,
                    R.drawable.warning
                )
            }else {
                if (!signinPasswordTxt.matches(Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$"))) {
                    toastOrEgg(
                        "올바른 비밀번호 형식이 아닙니다",
                        0,
                        R.color.black,
                        R.color.white,
                        R.drawable.warning
                    )
                }else{
                    onSignInBtn()
                }
            }
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

    private fun onSignInBtn() {

        auth.signInWithEmailAndPassword(binding.signInId.text.toString(), binding.signInPwd.text.toString())
            //버튼을 눌렀을때
            .addOnCompleteListener {
                    toastOrEgg(
                        "잠시만 기다려주세요",
                        0,
                        R.color.black,
                        R.color.white,
                        R.drawable.check
                    )
            }
            //계정이 있을때
            .addOnSuccessListener {
                toastOrEgg(
                    "로그인 성공",
                    0,
                    R.color.black,
                    R.color.white,
                    R.drawable.check
                )
            }
            //계정이 없을때
            .addOnFailureListener {
                if (it.toString() == "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password.") {
                    toastOrEgg(
                        "암호가 올바르지 않습니다",
                        0,
                        R.color.black,
                        R.color.white,
                        R.drawable.warning
                    )
                }else if(it.toString() == "com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted."){
                    toastOrEgg(
                        "존재하지 않는 이메일입니다",
                        0,
                        R.color.black,
                        R.color.white,
                        R.drawable.warning
                    )
                }else{
                    toastOrEgg(
                        "로그인 실패",
                        0,
                        R.color.black,
                        R.color.white,
                        R.drawable.warning
                    )
                }

            }

        //주석 삭제X
//        database = FirebaseDatabase.getInstance().reference
//        database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val modelData = auth.currentUser?.uid?.let {
//                    snapshot.child(it).getValue(
//                        UserModel::class.java
//                    )
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }


}