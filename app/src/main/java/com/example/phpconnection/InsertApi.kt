package com.example.phpconnection

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InsertApi {

    @FormUrlEncoded
    @POST("insert.php")
    fun add(@Field("edtName") ename:String, @Field("edtCity") ecity:String, @Field("edtDeg") edesignation:String):Call<Model>

}