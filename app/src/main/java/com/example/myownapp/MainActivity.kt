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
            startActivity(timerIntent)
        })
    }
}