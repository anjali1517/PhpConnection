package com.example.phpconnection

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.phpconnection.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

lateinit var binding: ActivityMainBinding
   //lateinit var tv:TextView
    var url:String="http:/192.168.18.95/android/"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tv.setText("")

        var retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api:MyApi = retrofit.create(MyApi::class.java)
        var call:Call<List<Model>> = api.getModel()

        call.enqueue(object : Callback<List<Model>>{
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                 response.body()?.forEach {
                        binding.tv.append("No."+ it.eid.toString()+"\nName:"+it.ename.toString()+"\nCity:"+it.ecity+"\n\n"+it.edesignation)

                    }
                }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                binding.tv.text=t.message
            }
        })

    }
}