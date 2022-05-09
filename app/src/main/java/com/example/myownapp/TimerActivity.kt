package com.example.myownapp

import android.app.PictureInPictureParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.view.View
import android.widget.Button
import java.util.*

class TimerActivity : AppCompatActivity() {

    private val pipParamBuilder = PictureInPictureParams.Builder()
    private var time = 0
    private var timerTask : Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

//        val mainVideo = findViewById<VideoView>(R.id.main_video)
//        mainVideo.run {
//            setOnCompletionListener { it.start() }
//            setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.sample}"))
//            start()
//        }

        val aspectRational = Rational(100, 50)
        pipParamBuilder?.let { ppbuilder ->
            ppbuilder.setAspectRatio(aspectRational)?.build()
        }

        val button1 = findViewById<Button>(R.id.activatePip)
        button1.setOnClickListener(View.OnClickListener {
            enterPictureInPictureMode(pipParamBuilder.build())
        })

        val startBtn = findViewById<Button>(R.id.startBtn)
    }
}