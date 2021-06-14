package com.cotion.it_consultiong.network


import com.cotion.it_consultiong.model.meal_model.MealModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("hub/mealServiceDietInfo/")
    fun getInfo(
            //  인증키
            @Query("KEY") KEY: String = "f50a392c9e2647e4a030ca8f95d7c568",
            //호출 문서(xml, json)
            @Query("Type") Type: String = "json",
            //시도교육청코드
            @Query("ATPT_OFCDC_SC_CODE") ATPT_OFCDC_SC_CODE: String = "F10",
            //표준학교코드
            @Query("SD_SCHUL_CODE") SD_SCHUL_CODE: String = "7380292",
            //급식일자
            @Query("MLSV_YMD") MLSV_YMD: String,
            //식사코드
            @Query("MMEAL_SC_CODE") MMEAL_SC_CODE: Int


    ): Call<MealModel>
}