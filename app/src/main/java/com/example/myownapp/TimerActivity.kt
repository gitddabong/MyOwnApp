package com.example.myownapp

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import java.util.*
import kotlin.concurrent.timer

class TimerActivity : AppCompatActivity() {

    private val pipParamBuilder = PictureInPictureParams.Builder()
    private var time = 0
    private var timerTask : Timer? = null
    private var txtTime : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

//        val mainVideo = findViewById<VideoView>(R.id.main_video)
//        mainVideo.run {
//            setOnCompletionListener { it.start() }
//            setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.sample}"))
//            start()
//        }

        val aspectRational = Rational(16, 9)
        pipParamBuilder?.let { ppbuilder ->
            ppbuilder.setAspectRatio(aspectRational)?.build()
        }

        val button1 = findViewById<Button>(R.id.activatePip)
        button1.setOnClickListener(View.OnClickListener {
            enterPictureInPictureMode(pipParamBuilder.build())
        })

        txtTime = findViewById<TextView>(R.id.txtTime)

        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener(View.OnClickListener {
            startTimer()
        })

        val stopBtn = findViewById<Button>(R.id.stopBtn)
        stopBtn.setOnClickListener(View.OnClickListener {
            stopTimer()
        })

        val resetTimer = findViewById<Button>(R.id.resetTimer)
        resetTimer.setOnClickListener(View.OnClickListener {
            resetTimer()
        })
    }

//    override fun onPictureInPictureModeChanged(
//        isInPictureInPictureMode: Boolean,
//        newConfig: Configuration?
//    ) {
//        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
//        if (isInPictureInPictureMode) {
//            Log.d("log", "pip 활성화")
//
//        } else {
//            Log.d("log", "pip 비활성화")
//        }
//    }


    private fun startTimer() {
        timerTask = kotlin.concurrent.timer(period = 10) {
            time++

            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                txtTime?.text = "${sec} : ${milli}"
            }
        }
    }

    private fun stopTimer() {
        timerTask?.cancel()
    }

    private fun resetTimer() {
        timerTask?.cancel()

        time = 0
        txtTime?.text = "00:00"
    }

}

