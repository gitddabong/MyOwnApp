package com.example.myownapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pipBtn = findViewById<Button>(R.id.pipBtn) as Button
        pipBtn.setOnClickListener(View.OnClickListener {
            val timerIntent = Intent(this, TimerActivity::class.java)
//            timerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(timerIntent)
        })

        val confBtn = findViewById<Button>(R.id.confBtn) as Button
        confBtn.setOnClickListener(View.OnClickListener {
            val confIntent = Intent(this, )

        })
    }
}