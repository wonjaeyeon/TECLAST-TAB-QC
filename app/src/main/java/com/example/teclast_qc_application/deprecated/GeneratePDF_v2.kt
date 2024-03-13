package com.example.teclast_qc_application.deprecated

//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.Canvas
//import android.graphics.pdf.PdfDocument
//import android.view.View
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.ComposeView
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.LifecycleCoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//
//@Composable
//fun MyComposeContent() {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(text = "QC Inspection: John Doe", style = MaterialTheme.typography.h6)
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = "Date: 2024-03-05", style = MaterialTheme.typography.body1)
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(text = "Device Specification", style = MaterialTheme.typography.h5)
//        Spacer(modifier = Modifier.height(8.dp))
//        // List your device specs here
//        Text(text = "CPU: 2.6 GHz Octa-core", style = MaterialTheme.typography.body1)
//        // Add more specs as needed
//    }
//}
//
////fun renderComposeToBitmap(context: Context, content: @Composable () -> Unit): Bitmap {
////    val composeView = ComposeView(context).apply {
////        setContent {
////            content()
////        }
////    }
////
////    // Measure and layout
////    val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
////    composeView.measure(measureSpec, measureSpec)
////    composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)
////
////    val bitmap = Bitmap.createBitmap(composeView.width, composeView.height, Bitmap.Config.ARGB_8888)
////    val canvas = Canvas(bitmap)
////    composeView.draw(canvas)
////
////    return bitmap
////}
////
////fun generatePDFWithBitmap(context: Context, directory: File) {
////    val pdfDocument = PdfDocument()
////    val bitmap = renderComposeToBitmap(context,  content = { MyComposeContent() })
////
////    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
////    val page = pdfDocument.startPage(pageInfo)
////
////    val canvas = page.canvas
////    canvas.drawBitmap(bitmap, 0f, 0f, null)
////    pdfDocument.finishPage(page)
////
////    val file = File(directory, "Test_Report_From_Compose.pdf")
////    pdfDocument.writeTo(FileOutputStream(file))
////    pdfDocument.close()
////}
//
//fun generatePDFWithBitmap(
//    context: Context,
//    directory: File,
//    lifecycleScope: LifecycleCoroutineScope,
//    content: @Composable () -> Unit
//) {
//    lifecycleScope.launch {
//        // Switch to the Main (UI) thread
//        val bitmap = withContext(Dispatchers.Main) {
//            // Ensure ComposeView is attached to a window and UI operations are safe
//            renderComposeToBitmap(context, content)
//        }
//
//        val pdfDocument = PdfDocument()
//        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
//        val page = pdfDocument.startPage(pageInfo)
//
//        val canvas = page.canvas
//        canvas.drawBitmap(bitmap, 0f, 0f, null)
//        pdfDocument.finishPage(page)
//
//        val file = File(directory, "Test_Report_From_Compose.pdf")
//        try {
//            pdfDocument.writeTo(FileOutputStream(file))
//            // Notify user or update UI here on success
//        } catch (e: IOException) {
//            e.printStackTrace()
//            // Handle error
//        } finally {
//            pdfDocument.close()
//        }
//    }
//}
//
//fun renderComposeToBitmap(context: Context, content: @Composable () -> Unit): Bitmap {
//    val composeView = ComposeView(context).apply {
//        // Set content and measure/layout
//        setContent {
//            content()
//        }
//    }
//    // Dummy size setup, customize as per your requirement
//    val widthSpec = View.MeasureSpec.makeMeasureSpec(800, View.MeasureSpec.EXACTLY)
//    val heightSpec = View.MeasureSpec.makeMeasureSpec(1200, View.MeasureSpec.EXACTLY)
//    composeView.measure(widthSpec, heightSpec)
//    composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)
//
//    val bitmap = Bitmap.createBitmap(composeView.width, composeView.height, Bitmap.Config.ARGB_8888)
//    val canvas = Canvas(bitmap)
//    composeView.draw(canvas)
//    return bitmap
//}