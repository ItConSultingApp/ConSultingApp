package com.cotion.it_consultiong

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/List") //서버에 GET요청을 할 주소
    fun  getList(): Call<JsonObject> // JSON
}