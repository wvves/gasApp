package com.example.gasapp.push_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.gasapp.R

class PushNotificationService {

    companion object {
        private const val CHANNEL_ID = "gas_app_channel_id"
        private const val CHANNEL_NAME = "gas_app_channel_name"
        private const val CHANNEL_DESCRIPTION = "gas_app_channel_description"
        private const val ID = 1
        const val ACTION_CONFIRM = "ACTION_CONFIRM"
        const val ACTION_DENY = "ACTION_DENY"
        const val ACTION = "action"

        fun showNotification(context: Context, title: String, message: String) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Подтвердить", createPendingIntent(context, ACTION_CONFIRM))
                .addAction(0, "Отменить", createPendingIntent(context, ACTION_DENY))

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

            notificationManager?.notify(ID, builder.build())
        }

        private fun createPendingIntent(context: Context, action: String): PendingIntent? {
            val intent = Intent(context, PushNotificationReceiver::class.java)
            intent.setAction(action)
            if (ACTION_CONFIRM == action) {
                intent.putExtra(ACTION, ACTION_CONFIRM);
            } else if (ACTION_DENY == action) {
                intent.putExtra(ACTION, ACTION_DENY);
            }
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}