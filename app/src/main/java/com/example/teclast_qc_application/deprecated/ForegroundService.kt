package com.example.teclast_qc_application.deprecated

//import android.app.Notification
//import android.app.PendingIntent
//import android.app.Service
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.os.IBinder
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class VolumeButtonService : Service() {
//
//    private var pressCount = 0
//    private var serviceActive = true
//
//    override fun onBind(intent: Intent): IBinder? {
//        return null
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            0,
//            Intent(this, MainActivity::class.java),
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val notification = Notification.Builder(this, "YOUR_CHANNEL_ID")
//            .setContentTitle("Volume Button Service")
//            .setContentText("Listening for volume button presses...")
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        startForeground(1, notification)
//
//        val reportFile = applicationContext.getFileStreamPath("report.txt")
//        serviceActive = reportFile == null || !reportFile.exists()
//
//        if (serviceActive) {
//            val volumeUpReceiver = object : BroadcastReceiver() {
//                override fun onReceive(context: Context, intent: Intent) {
//                    if (intent.action == "android.media.VOLUME_CHANGED_ACTION") {
//                        pressCount++
//                        if (pressCount == 3) {
//                            startActivity(Intent(this@VolumeButtonService, MainActivity::class.java))
//                            pressCount = 0
//                        }
//
//                        GlobalScope.launch {
//                            delay(500)
//                            pressCount = 0
//                        }
//                    }
//                }
//            }
//
//            val filter = IntentFilter("android.media.VOLUME_CHANGED_ACTION")
//            registerReceiver(volumeUpReceiver, filter)
//        } else {
//            stopSelf()
//        }
//
//        return START_STICKY
//    }
//}
