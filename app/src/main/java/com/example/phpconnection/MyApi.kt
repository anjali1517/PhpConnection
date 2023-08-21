package com.example.phpconnection

import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("displayold.php")
fun getModel():Call<List<Model>>
}