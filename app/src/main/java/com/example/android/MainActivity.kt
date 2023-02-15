package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import test.TestOuterClass.Foo
import kotlin.concurrent.thread



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRequest.setOnClickListener {
            thread {
                callFastAsync()
            }
        }
    }

    private fun callFastAsync() {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://192.168.88.239:8000/").build();
        val serializable = try {
            val response = client.newCall(request).execute()
            val respData = response.body()!!.bytes()
            val hi = Foo.newBuilder().mergeFrom(respData).build();
            Log.d("resp", hi.toString())
        } catch (err: Error) {
            Log.d("error", err.toString())
        }
    }
}