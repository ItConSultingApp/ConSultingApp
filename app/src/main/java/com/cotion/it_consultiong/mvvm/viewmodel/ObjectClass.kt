package com.cotion.it_consultiong.mvvm.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.R
import com.fullpagedeveloper.toastegg.toastOrEgg

open class ObjectClass : AppCompatActivity() {


    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun eggToast(text: String, image: Int) {
        toastOrEgg(
            text,
            0,
            R.color.black,
            R.color.white,
            image
        )
    }
//    fun stIntent(context: Context, activity: Activity) {
//        val intent = Intent(context, activity::class.java)
//        startActivity(intent)
//        finish()
//    }

}