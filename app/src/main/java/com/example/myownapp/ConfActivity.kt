package com.example.myownapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log

class ConfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conf)

        var TAG = "call test"

//        val telephonyManager : TelephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
//        val telephonyCallback : TelephonyCallback = getSystemService(TELE) as TelephonyCallback
//        telephonyManager.registerTelephonyCallback(mainExecutor,telephonyCallback
//
//
//        )
        Log.d(TAG, "시작!")

        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, incommingNumber: String) {
                when (state) {
                    TelephonyManager.CALL_STATE_RINGING -> Log.d(
                        TAG,
                        "CALL_STATE_RINGING:$incommingNumber"
                    )
                    TelephonyManager.CALL_STATE_IDLE -> Log.d(TAG, "CALL_STATE_IDLE")
                    TelephonyManager.CALL_STATE_OFFHOOK -> Log.d(TAG, "CALL_STATE_OFFHOOK")
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE)
    }
}