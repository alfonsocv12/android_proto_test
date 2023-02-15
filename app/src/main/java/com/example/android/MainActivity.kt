package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import src.incoming.players.http_responses.HttpResponses
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
        val request = Request.Builder().url("http://192.168.1.65:8000/players").build();
        try {
            val response = client.newCall(request).execute()
            val respData = response.body()!!.bytes()
            val players = HttpResponses.GetPlayers
                .newBuilder()
                .mergeFrom(respData)
                .build();
            Log.d("resp", players.toString())
        } catch (err: Error) {
            Log.d("error", err.toString())
        }
    }
}