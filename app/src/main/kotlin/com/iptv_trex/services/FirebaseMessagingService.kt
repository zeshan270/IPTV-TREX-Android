package com.iptv_trex.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.iptv_trex.MainActivity
import com.iptv_trex.R
import timber.log.Timber

class FirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "iptv_trex_notifications"
        private const val CHANNEL_NAME = "IPTV TREX Notifications"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("Message received from: ${remoteMessage.from}")

        // Check if message contains a notification payload
        remoteMessage.notification?.let {
            Timber.d("Message Notification Body: ${it.body}")
            sendNotification(it.title ?: "IPTV TREX", it.body ?: "")
        }

        // Check if message contains a data payload
        if (remoteMessage.data.isNotEmpty()) {
            Timber.d("Message data payload: ${remoteMessage.data}")
            handleDataMessage(remoteMessage.data)
        }
    }

    override fun onNewToken(token: String) {
        Timber.d("Refreshed token: $token")
        sendTokenToServer(token)
    }

    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for IPTV TREX app"
                enableVibration(true)
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        // Handle custom data from server
        val action = data["action"]
        val content = data["content"]

        Timber.d("Data message - Action: $action, Content: $content")

        when (action) {
            "new_stream" -> {
                Timber.d("New stream available: $content")
            }
            "special_offer" -> {
                Timber.d("Special offer: $content")
                sendNotification("Special Offer", content ?: "")
            }
            "maintenance" -> {
                Timber.d("Maintenance notice: $content")
                sendNotification("Maintenance Notice", content ?: "")
            }
        }
    }

    private fun sendTokenToServer(token: String) {
        // Send the FCM token to your server for later use
        Timber.d("Sending token to server: $token")
        // In a real app, you would make a network call here to register the token
    }
}
