package com.example.it_consultiong.mvvm.viewmodel

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class ObjectClass : AppCompatActivity() {


    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


//    fun stIntent(context: Context, activity: Activity) {
//        val intent = Intent(context, activity::class.java)
//        startActivity(intent)
//        finish()
//    }

}