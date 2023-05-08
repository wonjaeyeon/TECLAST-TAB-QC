package com.example.teclast_qc_application

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

//class BootCompletedReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//            // 디바이스 부팅 완료 후 알림 생성
//            val channelId = "boot_completed_notification_channel"
//            createNotificationChannel(context, channelId)
//            createNotification(context, channelId)
//        }
//    }
//
//    private fun createNotification(context: Context, channelId: String) {
//        val notification = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("테스트 알림")
//            .setContentText("디바이스가 켜졌습니다.")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .build()
//
//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(1, notification)
//    }
//
//    private fun createNotificationChannel(context: Context, channelId: String) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Boot Completed Notifications"
//            val descriptionText = "Notifications for boot completed events"
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(channelId, name, importance).apply {
//                description = descriptionText
//            }
//
//            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//}

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val channelId = "boot_completed_notification_channel"
            createNotificationChannel(context, channelId)
            showNotification(context, channelId)
        }
    }

    private fun createNotificationChannel(context: Context, channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Boot Completed Channel"
            val descriptionText = "Channel for boot completed notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


//    private fun showNotification(context: Context, channelId: String) {
//        val notification = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("테스트 알림")
//            .setContentText("디바이스가 켜졌습니다.")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .build()
//
//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(0, notification)
//    }


//    private fun showNotification(context: Context, channelId: String) {
//    // 앱을 실행할 인텐트 생성
//    val launchIntent = Intent(context, MainActivity::class.java).apply {
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//    // PendingIntent 생성
//    val pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//    // 알림에 PendingIntent 설정
//    val notification = NotificationCompat.Builder(context, channelId)
//        .setSmallIcon(R.drawable.ic_launcher_background)
//        .setContentTitle("TECLAST QC APP")
//        .setContentText("테스트를 시작하시겠습니까?")
//        .setPriority(NotificationCompat.PRIORITY_HIGH)
//        .setContentIntent(pendingIntent)
//        .setAutoCancel(true) // 알림을 클릭하면 자동으로 사라지도록 설정
//        .build()
//
//    val notificationManager = NotificationManagerCompat.from(context)
//    notificationManager.notify(0, notification)
//    }

    private fun showNotification(context: Context, channelId: String) {
        // 앱을 실행할 인텐트 생성
        val launchIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        // PendingIntent 생성
        val pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Full-screen intent 생성
        val fullScreenPendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // 알림에 PendingIntent 설정
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("TECLAST QC APP")
            .setContentText("테스트를 시작하시겠습니까?")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // 알림을 클릭하면 자동으로 사라지도록 설정
            .setFullScreenIntent(fullScreenPendingIntent, true) // 알림이 전체 화면으로 표시되도록 설정
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // 잠금 화면에서 알림이 표시되도록 설정
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(0, notification)
    }

}
