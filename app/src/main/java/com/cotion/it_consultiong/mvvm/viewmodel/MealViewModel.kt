package com.cotion.it_consultiong.mvvm.viewmodel

import android.app.Application
import android.content.ContentValues
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.cotion.it_consultiong.UI.Main.BoardFragment
import com.cotion.it_consultiong.UI.Main.HomeFragment.Companion.TAG
import com.example.school_cafeteria.Model.meal_model
import com.example.school_cafeteria.Ui.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)

class MealViewModel(application: Application) : AndroidViewModel(application) {


    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("YYYYMMdd")
    val formatter_custon = DateTimeFormatter.ofPattern("YYYY-MM-dd") //text 에 보여질거
    val formatted = current.format(formatter)
    val formatted_custon = current.format(formatter_custon)


    fun Retrofit(time: Int, meal: TextView) {
        RetrofitBuilder.service.getInfo(MLSV_YMD = formatted, MMEAL_SC_CODE = time).enqueue(object :
            Callback<meal_model> {
            override fun onFailure(call: Call<meal_model>, t: Throwable) {
                t.printStackTrace()
                Log.d(ContentValues.TAG, "MainActivity - onFailure()")
            }

            override fun onResponse(
                call: Call<meal_model>,
                response: Response<meal_model>
            ) {

                if (response.isSuccessful) {
                    val res = response.body()?.mealServiceDietInfo?.get(1)?.row



                    if (res != null) {
                        for (i in res.indices) {
                            val obj = res[i]
                            val row = obj.DDISH_NM.replace("<br/>", "\n").replace("/", "")
                                .replace("*", "").replace(".", "").replace("1", "").replace("2", "")
                                .replace("3", "").replace("4", "").replace("5", "").replace("6", "")
                                .replace("7", "").replace("8", "").replace("9", "").replace("0", "")
                            Log.d(TAG, "onResponse: $row")

                            when (time) {

                                1 -> meal.setText(row)
                                2 -> meal.setText(row)
                                3 -> meal.setText(row)


                            }

                        }
                    } else {
                        Log.d(ContentValues.TAG, "onResponse: ${response.code()}")
                    }

                }
            }


        })

    }

}