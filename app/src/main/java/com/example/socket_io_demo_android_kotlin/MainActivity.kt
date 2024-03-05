package com.example.socket_io_demo_android_kotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The following lines connects the Android app to the server.
        SocketHandler.setSocket()
        SocketHandler.establishConnection()

        val counterBtn = findViewById<Button>(R.id.counterBtn)
        val countTextView = findViewById<TextView>(R.id.countTextView)

        val mSocket = SocketHandler.getSocket() // we getting the instance of socket

        counterBtn.setOnClickListener{
            mSocket.emit("counter")// we use .emit to send the data to the server
            // this "counter" is the variable on the server
        }
        // getting the data from the server
        mSocket.on("counter") { args ->
            if (args[0] != null) {
                val counter = args[0] as Int
                runOnUiThread {
                    countTextView.text = counter.toString()
                }
            }
        }
    }
}