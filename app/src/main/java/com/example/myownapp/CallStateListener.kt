package com.example.myownapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log

abstract class CallStateListener : BroadcastReceiver() {

    val TAG : String? = "call test"

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val bundle : Bundle? = intent?.extras

        if (action.equals("android.intent.action.PHONE_STATE")) {
            val state : String? = bundle?.getString(TelephonyManager.EXTRA_STATE)
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Log.d(TAG, "EXTRA_STATE_IDLE")
            } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Log.d(TAG, "EXTRA_STATE_RINGING INCOMMING NUMBER : " + bundle?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER))
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Log.d(TAG, "EXTRA_STATE_OFFHOOK")
            }
        } else if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Log.d(TAG, "OUTGOING CALL : " + bundle?.getString(Intent.EXTRA_PHONE_NUMBER))
        }

    }



}