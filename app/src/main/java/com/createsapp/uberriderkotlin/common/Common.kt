package com.createsapp.uberriderkotlin.common

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.createsapp.uberriderkotlin.R
import com.createsapp.uberriderkotlin.model.DriverGeoModel
import com.createsapp.uberriderkotlin.model.RiderModel
import com.google.android.gms.maps.model.Marker

object Common {
    val markerList: MutableMap<String, Marker> = HashMap<String,Marker>()
    val DRIVER_INFO_REFERENCE: String = "DriverInfo"
    val driversFound: MutableSet<DriverGeoModel> = HashSet<DriverGeoModel>()
    val DRIVERS_LOCATION_REFERENCES: String = "DriversLocation" //Same as Server app
    val TOKEN_REFERENCE: String = "Token"
    var currentRider: RiderModel? = null
    const val RIDER_INFO_REFERENCE: String = "Riders"
    val NOTI_BODY: String = "body"
    val NOTI_TITLE: String = "title"

    fun buildWelcomeMessage(): String {
        return StringBuilder("Welcome, ")
            .append(currentRider!!.firstName)
            .append(" ")
            .append(currentRider!!.lastName)
            .toString()
    }

    fun showNotification(
        context: Context,
        id: Int,
        title: String?,
        body: String?,
        intent: Intent?
    ) {
        var pendingIntent: PendingIntent? = null
        if (intent != null)
            pendingIntent =
                PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val NOTIFICATION_CHANNEL_ID = "creates_app"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, "Uber Clone",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "Ubser Clone"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        builder.setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_baseline_directions_car_24
                )
            )

        if (pendingIntent != null)
            builder.setContentIntent(pendingIntent)
        val notification = builder.build()
        notificationManager.notify(id, notification)
    }

    fun buildName(firstName: String?, lastName: String?): String? {
        return java.lang.StringBuilder(firstName).append(" ").append(lastName).toString()
    }

}