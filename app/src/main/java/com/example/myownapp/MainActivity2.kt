package com.example.myownapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myownapp.R
import android.telephony.TelephonyManager
import android.telephony.PhoneStateListener
import android.util.Log

class MainActivity2 : AppCompatActivity() {
    var TAG = "confActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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