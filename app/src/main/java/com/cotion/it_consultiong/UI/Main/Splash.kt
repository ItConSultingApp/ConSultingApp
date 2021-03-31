package com.cotion.it_consultiong.UI.Main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.FragmentMaInActivity
import com.cotion.it_consultiong.UI.LoginMainActivity
import com.cotion.it_consultiong.UI.LoginMainActivity.Companion.AUTHTAG
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userName
import com.cotion.it_consultiong.data.data_model.signInUserInfo
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.InternalCoroutinesApi

class Splash : AppCompatActivity() {

    companion object{
        lateinit var auth : FirebaseAuth
        lateinit var database : DatabaseReference
        lateinit var uid: String

        //로그인된 유저 정보
        var userName: String?= null
        var userEmail: String? = null
        var userPassword: String? = null
        var userGrade: String?= null
        var userClass: String?= null
        var userNumber: String?= null
        var userJob: String?= null
        var userProfile: Uri?= null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }


    //이게 있어야 LoginMainActivity로 intent가 되는데 뭔지는 모르겠음
    @InternalCoroutinesApi
    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        Log.d(AUTHTAG,"${auth.currentUser.uid}")
        Handler().postDelayed(
            {
                if (auth == null){
                    goNext(false)
                }else{
                    uid = auth.currentUser.uid
                    getUserInfo()
                }
            },
            1500
        )

    }

    @InternalCoroutinesApi
    private fun getUserInfo(){
        database = FirebaseDatabase.getInstance().reference
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.child("users").child(uid).getValue(signInUserInfo::class.java)
                userName = post?.userName
                userGrade = post?.userGrade
                userClass = post?.userClass
                userNumber = post?.userNumber
                userEmail = post?.userEmail
                userPassword = post?.userPassword
                userJob = post?.userJob
                Log.d("리그", post?.userClass.toString())
                goNext(true)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                toastOrEgg(
                    "예기치 않은 오류가 발생했습니다",
                    0,
                    R.color.black,
                    R.color.white,
                    R.drawable.warning
                )
            }
        }
        database.addListenerForSingleValueEvent (postListener)
    }

    @InternalCoroutinesApi
    private fun goNext(new:Boolean){
        if(new){
            val intent = Intent(this,FragmentMaInActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,LoginMainActivity::class.java)
            startActivity(intent)
        }

    }

}