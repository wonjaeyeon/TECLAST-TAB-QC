package com.example.teclast_qc_application.deprecated

//import android.app.Service
//import android.content.Intent
//import android.os.Handler
//import android.os.IBinder
//
//class PowerButtonService : Service() {
//
//    private val handler = Handler()
//    private var powerButtonPressCount = 0
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        powerButtonPressCount++
//
//        if (powerButtonPressCount == 3) {
//            powerButtonPressCount = 0
//
//            // Start your app here
//            val launchIntent = Intent(this, MainActivity::class.java)
//            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(launchIntent)
//        }
//
//        handler.removeCallbacks(resetCounterRunnable)
//        handler.postDelayed(resetCounterRunnable, 1000)
//
//        return START_STICKY
//    }
//
//    private val resetCounterRunnable = Runnable {
//        powerButtonPressCount = 0
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//}
