package com.cotion.it_consultiong.mvvm.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class ObjectClass : AppCompatActivity() {


    fun showToast(context:Context,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


//    fun stIntent(context: Context, activity: Activity) {
//        val intent = Intent(context, activity::class.java)
//        startActivity(intent)
//        finish()
//    }

}