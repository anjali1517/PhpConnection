package com.example.phpconnection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.phpconnection.databinding.ActivityInsertBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class InsertActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertBinding
    var url:String="http:/192.168.18.95/android/"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInsertBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRead.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.btnInsert.setOnClickListener {
            process()
        }
    }

    private fun process() {

        var retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var myapi = retrofit.create(InsertApi::class.java)

        var call:Call<Model> = myapi.add(binding.edtName.text.toString(),binding.edtCity.text.toString(),binding.edtDeg.text.toString())

        call.enqueue(object : Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                binding.edtName.setText("")
                binding.edtCity.setText("")
                binding.edtDeg.setText("")
                Toast.makeText(this@InsertActivity,response.toString(),Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Toast.makeText(this@InsertActivity,t.toString(),Toast.LENGTH_LONG).show()

            }

        })
    }
}