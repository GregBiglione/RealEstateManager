package com.openclassrooms.realestatemanager.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.ui.activities.MainActivity

class MyFirebaseMessagingService: FirebaseMessagingService() {

    val NOTIFICATION_ID = 2109
    val NOTIFICATION_TAG = "FIRE_BASE_REAL_ESTATE_MANGER"
    val TAG = "NotificationService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null){
            val message: String? = remoteMessage.notification?.body
            sendVisualNotification(message)
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------- Send notification ----------------------------------------------
    //----------------------------------------------------------------------------------------------

    private fun sendVisualNotification(messageBody: String?) {

        //----------------------------- Create an Intent that will be shown when user will click on the Notification ------
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT)

        //----------------------------- Create a style for the notification ------------------------
        val inboxStyle = NotificationCompat.InboxStyle()
        inboxStyle.setBigContentTitle(getString(R.string.notification_title))
        inboxStyle.addLine(messageBody)

        //----------------------------- Create a Channel -------------------------------------------
        val channelId = getString(R.string.default_notification_channel_id)

        //----------------------------- Build a Notification object --------------------------------
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_baseline_hot_tub_24)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("House added in database")
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setStyle(inboxStyle)

        //----------------------------- Add the Notification to the Notification Manager and show it -------------
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        //----------------------------- Support Version >= Android 8 -------------------------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName: CharSequence = "Message from Real Estate Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance)
            notificationManager.createNotificationChannel(channel)
        }

        //----------------------------- Show notification ------------------------------------------
        notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, notificationBuilder.build())
    }
}