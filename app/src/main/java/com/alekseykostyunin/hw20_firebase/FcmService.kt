package com.alekseykostyunin.hw20_firebase

import android.annotation.SuppressLint
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FcmService : FirebaseMessagingService(){
    @SuppressLint("MissingPermission")
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val notification = NotificationCompat.Builder(this, App.NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(message.data["nickname"])
        .setContentText(message.data["message"] + convertToDate(message.data["timestamp"]))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        .build()

        NotificationManagerCompat.from(this).notify(1, notification)

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("New token: $token")
    }

    private fun convertToDate(timestamp: String?): String{
        timestamp ?: return ""
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date(timestamp.toLong() * 1000))
    }
}