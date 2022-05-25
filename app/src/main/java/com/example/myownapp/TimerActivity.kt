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
    private var timerFlag = false

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
            enterPictureInPictureMode(
                pipParamBuilder
                    .setSeamlessResizeEnabled(false)
                    .build()
            )
        })

        txtTime = findViewById<TextView>(R.id.txtTime)

        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener(View.OnClickListener {
            if (!timerFlag) {
                startTimer()
            }
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
        timerFlag = true
        timerTask = kotlin.concurrent.timer(period = 10) {
            time++

            val sec = time / 100
            val min = sec / 60
            val milli = time % 100

            var min_str : String = ""
            if (min < 10) {
                min_str = "0" + min.toString()
            } else {
                min_str = min.toString()
            }

            var sec_str : String = ""
            var sec_mod = sec % 60
            if (sec_mod < 10) {
                sec_str = "0" + sec_mod.toString()
            } else {
                sec_str = sec_mod.toString()
            }

            var milli_str : String = ""
            if (milli < 10) {
                milli_str = "0" + milli.toString()
            } else {
                milli_str = milli.toString()
            }

            runOnUiThread {
                txtTime?.text = "${min_str}:${sec_str}:${milli_str}"
            }
        }
    }

    private fun stopTimer() {
        timerFlag = false
        timerTask?.cancel()
    }

    private fun resetTimer() {
        timerFlag = false
        timerTask?.cancel()

        time = 0
        txtTime?.text = "00:00:00"
    }

}

