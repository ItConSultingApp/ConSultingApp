package com.cotion.it_consultiong.mvvm.models

import android.content.Context
import android.widget.Toast
import com.cotion.it_consultiong.R

open class ObjectClass  {


    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


//    fun stIntent(context: Context, activity: Activity) {
//        val intent = Intent(context, activity::class.java)
//        startActivity(intent)
//        finish()
//    }

}