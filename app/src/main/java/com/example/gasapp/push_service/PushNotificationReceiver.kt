package com.example.gasapp.push_service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.gasapp.MainActivity


class PushNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.getStringExtra(PushNotificationService.ACTION).orEmpty()
        if (action.isNotEmpty()) {
            when (action) {
                PushNotificationService.ACTION_CONFIRM -> {
                    val mainIntent = Intent(context, MainActivity::class.java)
                    mainIntent.putExtra(PushNotificationService.ACTION, PushNotificationService.ACTION_CONFIRM)
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    context?.startActivity(mainIntent)
                }

                PushNotificationService.ACTION_DENY -> {
                    val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                    notificationManager?.cancel(1)
                }
            }
        }
    }
}