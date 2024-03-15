package com.teclast_korea.teclast_qc_application.home.pdf_export.generate_pdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import com.teclast_korea.teclast_qc_application.R
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat

//fun generatePDF(context: Context, directory: File, state: TestResultState, onEvent: (TestResultEvent) -> Unit, deviceSpec :  List<Pair<String, String>>,  testMode : String) {
//    val pageHeight = 1600
//    val pageWidth = 1200
//    val pdfDocument = PdfDocument()
//    val paint = Paint()
//    val titlePaint = Paint()
//    val myPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
//    val myPage = pdfDocument.startPage(myPageInfo)
//    val canvas: Canvas = myPage.canvas
//
//    val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd, HH:mm")
//    val date = dateFormat.format(System.currentTimeMillis())
//
//    var yPos = 40f
//
//    // Drawing the bitmap at the top right
//    val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.teclast_korea))
//    val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 60, 60, false)
//    canvas.drawBitmap(scaleBitmap!!, 40f, 40f, paint)
//
//    // Drawing QC Inspection's Name and Date
//    titlePaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//    titlePaint.textSize = 30f
//    titlePaint.color = ContextCompat.getColor(context, R.color.black)
//    val qcName = "QC Test: $testMode"
//    val qcDate = "Date: $date"
//    canvas.drawText(qcName, 160f, 80f, titlePaint) // Adjust position as needed
//    canvas.drawText(qcDate, 160f, 120f, titlePaint) // Adjust position as needed
//
//    // Drawing Device Spec Title
//    val specTitle = "Device Specification"
//    paint.textSize = 22f
//    canvas.drawText(specTitle, 40f, 240f, paint) // Adjust Y position as needed for title
//
//    // Drawing Device Spec Details in a List
//    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
//    paint.textSize = 18f
//    paint.color = ContextCompat.getColor(context, R.color.black)
//    val specs = TestReportList(context = context, state = state, onEvent = onEvent)
//    yPos = 270f
//    specs.forEach { spec ->
//        canvas.drawText(spec.toString(), 40f, yPos, paint)
//        yPos += 30f // Adjust spacing as needed
//    }
//
//    yPos += 60f // Adjust spacing as needed
//
//
//    // Drawing Device Test Result Title
//    val resultTitle = "Device Test Result"
//    paint.textSize = 22f
//    canvas.drawText(resultTitle, 40f, yPos, paint) // Adjust Y position as needed for title
//    yPos += 30f // Adjust spacing as needed
//
//    // Drawing Device Test Result Details in a List
//    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
//    paint.textSize = 18f
//    paint.color = ContextCompat.getColor(context, R.color.black)
//    val results = TestReportList(context = context, state = state, onEvent = onEvent)
//    results.forEach { result ->
//        canvas.drawText(result.toString(), 40f, yPos, paint)
//        yPos += 30f // Adjust spacing as needed
//    }
//
//    yPos += 10020f // Adjust spacing as needed
//
//
//    canvas.drawText("Thank you for your attention.", 40f, yPos, paint) // Adjust Y position as needed for title
//
//
//
//    pdfDocument.finishPage(myPage)
//
//    val file = File(directory, "Test_Report.pdf")
//
//    // Check if the file already exists, delete it if it does
//    if (file.exists()) {
//        file.delete()
//    }
//
//    try {
//        pdfDocument.writeTo(FileOutputStream(file))
//        Toast.makeText(context, "PDF file generated successfully", Toast.LENGTH_SHORT).show()
//    } catch (ex: IOException) {
//        ex.printStackTrace()
//    }
//    pdfDocument.close()
//}


// Enum for title sizes
enum class TitleSize {
    BIG, MEDIUM, SMALL
}

fun generatePDF(
    context: Context,
    directory: File,
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    deviceSpec: List<Pair<String, String>>,
    testMode: String = "Not Found",
    showToast: Boolean = false,
    testReportList: List<Pair<String, String>>
) {
    val pageHeight = 1600
    val pageWidth = 1200
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val titlePaint = Paint()

    var currentPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
    var currentPage = pdfDocument.startPage(currentPageInfo)
    var canvas: Canvas = currentPage.canvas

    val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd, HH:mm")
    val date = dateFormat.format(System.currentTimeMillis())

    val testSpecList: List<Pair<String, String>> = listOf(
        "TestMode" to testMode,
        "Date" to date,
    )
    Log.i("testReportList - generatePDF", testReportList.toString())

    var yPos = 40f


    fun checkAndAddNewPage(forceYPose: Float? = null) {
        if (forceYPose != null) {
            yPos = forceYPose
        }
        if (yPos >= pageHeight - 100) { // Check if we need a new page
            pdfDocument.finishPage(currentPage) // Finish current page
            currentPageInfo =
                PageInfo.Builder(pageWidth, pageHeight, pdfDocument.pages.size + 1).create() // Create new page
            currentPage = pdfDocument.startPage(currentPageInfo)
            canvas = currentPage.canvas
            yPos = 40f // Reset yPos for the new page
        }
    }

    fun drawTextAndCheckPage(text: String, xOffset: Float, titleSize: TitleSize, forceYPose: Float? = null) {
        checkAndAddNewPage(forceYPose = forceYPose)

        // Adjust text size based on title size
        when (titleSize) {
            TitleSize.BIG -> {
                paint.textSize = 30f
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            TitleSize.MEDIUM -> {
                paint.textSize = 26f
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            TitleSize.SMALL -> {
                paint.textSize = 18f
                paint.typeface =
                    Typeface.create(Typeface.DEFAULT, Typeface.NORMAL) // Set normal typeface for small title
                if (text == "PASS") {
                    paint.color = Color.Green.toArgb()
                } else if (text == "FAIL") {
                    paint.color = Color.Red.toArgb()
                } else {
                    paint.color = ContextCompat.getColor(context, R.color.black)
                }
            }
        }

        canvas.drawText(text, xOffset, yPos, paint)
        yPos += when (titleSize) {
            TitleSize.BIG -> 50f
            TitleSize.MEDIUM -> 40f
            TitleSize.SMALL -> 30f
        } // Adjust yPos based on title size
    }

    // Drawing the bitmap at the top right
    // Assuming drawableToBitmap() is correctly implemented
    val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.teclast_korea, null))
    if (bitmap != null) {
        val scaleBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, false)
        canvas.drawBitmap(scaleBitmap, 40f, 40f, paint)
    }

    yPos += 100 // Adjust yPos after drawing the bitmap

    // Setup title paint style
    titlePaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    titlePaint.textSize = 30f
    titlePaint.color = ContextCompat.getColor(context, R.color.black)


    // Drawing QC Inspection's Name and Date
    if (deviceSpec[0].second == "" || deviceSpec[0].second == "unknown") {
        drawTextAndCheckPage("QC TEST Report", 160f, forceYPose = 100f, titleSize = TitleSize.BIG)
    } else {
        drawTextAndCheckPage(
            "QC TEST Report_${deviceSpec[0].second}",
            160f,
            forceYPose = 100f,
            titleSize = TitleSize.BIG
        )
    }

    yPos += 100 // Adjust yPos after drawing the title

    // Draw Device Spec and Test Results
    // Assuming deviceSpec and results are Lists of Pair<String, String> you want to print
    drawTextAndCheckPage("Device Specification", 40f, TitleSize.MEDIUM)

    deviceSpec.forEach { spec ->
        drawTextAndCheckPage("${spec.first}: ${spec.second}", 40f, TitleSize.SMALL)
    }

    yPos += 100 // Adjust yPos after drawing Device Spec
    drawTextAndCheckPage("Device Test Description", 40f, TitleSize.MEDIUM)
    testSpecList.forEach { spec ->
        drawTextAndCheckPage("${spec.first}: ${spec.second}", 40f, TitleSize.SMALL)
    }

    yPos += 100 // Adjust yPos after drawing Device Spec
    drawTextAndCheckPage("Device Test Result", 40f, TitleSize.MEDIUM)
    testReportList.forEach { spec ->
        drawTextAndCheckPage("${spec.first}: ${spec.second}", 40f, TitleSize.SMALL)
    }


    // Once all drawing is done
    pdfDocument.finishPage(currentPage) // Don't forget to finish the last page

    val file = File(directory, "Test_Report.pdf")
    if (file.exists()) {
        file.delete()
    }

    try {
        pdfDocument.writeTo(FileOutputStream(file))
        if (showToast) {
            Toast.makeText(context, "PDF file generated successfully", Toast.LENGTH_SHORT).show()
        }

    } catch (ex: IOException) {
        ex.printStackTrace()
    } finally {
        pdfDocument.close()
    }
}

fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}
