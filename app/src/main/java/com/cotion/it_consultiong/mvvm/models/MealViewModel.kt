package com.cotion.it_consultiong.mvvm.models

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.cotion.it_consultiong.model.meal_model.MealModel
import com.cotion.it_consultiong.network.RetrofitBuilder
import com.cotion.it_consultiong.ui.main.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MealViewModel(application: Application) : AndroidViewModel(application) {


    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("YYYYMMdd")
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter_custom = DateTimeFormatter.ofPattern("YYYY-MM-dd") //text 에 보여질거
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter_custom_board = DateTimeFormatter.ofPattern("YYYY\nMM-dd") //text 에 보여질거
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted = current.format(formatter)
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted_custon = current.format(formatter_custom)
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted_custon_board = current.format(formatter_custom_board)


    fun Retrofit(time: Int, meal: TextView) {
        RetrofitBuilder.service.getInfo(MLSV_YMD = formatted, MMEAL_SC_CODE = time).enqueue(object :
            Callback<MealModel> {
            override fun onFailure(call: Call<MealModel>, t: Throwable) {
                t.printStackTrace()
                Log.d(ContentValues.TAG, "MainActivity - onFailure()")
            }

            override fun onResponse(
                call: Call<MealModel>,
                response: Response<MealModel>
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