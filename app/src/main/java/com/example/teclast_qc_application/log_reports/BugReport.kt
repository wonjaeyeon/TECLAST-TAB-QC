//package com.example.teclast_qc_application.log_reports
//
//import android.app.admin.BugreportManager
//import android.app.admin.BugreportManager.BugreportCallback
//import android.content.Context
//import android.util.Log
//
//class MyBugReportCallback(private val context: Context) : BugreportCallback() {
//
//    override fun onProgressUpdate(progress: Int) {
//        super.onProgressUpdate(progress)
//        Log.d("BugReportCallback", "Bugreport progress: $progress%")
//    }
//
//    override fun onFinished(bugreportInfo: BugreportManager.BugreportInfo) {
//        super.onFinished(bugreportInfo)
//        Log.d("BugReportCallback", "Bugreport finished. URI: ${bugreportInfo.bugreportUri}")
//        // Handle the generated bugreport, e.g., share it, save it, or analyze it.
//    }
//
//    override fun onError() {
//        super.onError()
//        Log.e("BugReportCallback", "Error generating bugreport")
//    }
//}