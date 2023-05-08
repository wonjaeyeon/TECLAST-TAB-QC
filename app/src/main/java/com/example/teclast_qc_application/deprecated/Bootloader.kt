package com.example.teclast_qc_application.deprecated

//class BootReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//            val reportFile = File(context.getExternalFilesDir(null), "report.txt")
//            if (!reportFile.exists()) {
//                // Start the app if the device is turned on and there's no report.txt file
//                val i = Intent(context, MainActivity::class.java)
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                context.startActivity(i)
//            }
//        }
//    }
//}

//class BootReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//            // Start the app if the device is turned on
//            val i = Intent(context, MainActivity::class.java)
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(i)
//        }
//    }
//}