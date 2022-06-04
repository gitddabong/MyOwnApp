package com.example.myownapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class IncomingCallBroadcastReceiver : BroadcastReceiver() {

    var phoneState : String? = ""

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val number = intent.getExtras()?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)




        } catch (e : Exception){
            e.printStackTrace()
        }

    }
}


