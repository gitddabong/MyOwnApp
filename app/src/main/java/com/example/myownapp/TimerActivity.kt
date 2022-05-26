package com.example.myownapp

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.*
import kotlin.concurrent.timer

private const val ACTION_STOPWATCH_CONTROL = "stopwatch_control"
private const val EXTRA_CONTROL_TYPE = "control_type"
private const val CONTROL_TYPE_CLEAR = 1
private const val CONTROL_TYPE_START_OR_PAUSE = 2

private const val REQUEST_CLEAR = 3
private const val REQUEST_START_OR_PAUSE = 4

class TimerActivity : AppCompatActivity() {

    private val pipParamBuilder = PictureInPictureParams.Builder()
    private var time = 0
    private var timerTask : Timer? = null
    private var txtTime : TextView? = null
    private var timerFlag = false



    private val broadcastReceiver = object : BroadcastReceiver() {

        // Called when an item is clicked.
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null || intent.action != ACTION_STOPWATCH_CONTROL) {
                return
            }
            when (intent.getIntExtra(EXTRA_CONTROL_TYPE, 0)) {
                CONTROL_TYPE_START_OR_PAUSE -> if (timerFlag) stopTimer() else startTimer()
                CONTROL_TYPE_CLEAR -> resetTimer()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        registerReceiver(broadcastReceiver, IntentFilter(ACTION_STOPWATCH_CONTROL))

        val button1 = findViewById<Button>(R.id.activatePip)
        button1.setOnClickListener(View.OnClickListener {
            enterPictureInPictureMode(updatePictureInPictureParams(timerFlag))
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


    private fun updatePictureInPictureParams(started : Boolean) : PictureInPictureParams {
//        binding.stopwatchBackground.getGlobalVisibleRect(visibleRect)
        val params = PictureInPictureParams.Builder()
            .setActions(
                listOf(
                    createRemoteAction(
                        R.drawable.ic_refresh_24dp,
                        R.string.clear,
                        REQUEST_CLEAR,
                        CONTROL_TYPE_CLEAR
                    ),
                    if (started) {
                        createRemoteAction(
                            R.drawable.ic_pause_24dp,
                            R.string.pause,
                            REQUEST_START_OR_PAUSE,
                            CONTROL_TYPE_START_OR_PAUSE
                        )
                    } else {
                        createRemoteAction(
                            R.drawable.ic_play_arrow_24dp,
                            R.string.start,
                            REQUEST_START_OR_PAUSE,
                            CONTROL_TYPE_START_OR_PAUSE
                        )
                    }
                )
            )
            .setAspectRatio(Rational(16, 9))
            .build()
        setPictureInPictureParams(params)
        return params
    }

    private fun createRemoteAction(
        @DrawableRes iconResId: Int,
        @StringRes titleResId: Int,
        requestCode: Int,
        controlType: Int
    ): RemoteAction {
        return RemoteAction(
            Icon.createWithResource(this, iconResId),
            getString(titleResId),
            getString(titleResId),
            PendingIntent.getBroadcast(
                this,
                requestCode,
                Intent(ACTION_STOPWATCH_CONTROL)
                    .putExtra(EXTRA_CONTROL_TYPE, controlType),
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

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

