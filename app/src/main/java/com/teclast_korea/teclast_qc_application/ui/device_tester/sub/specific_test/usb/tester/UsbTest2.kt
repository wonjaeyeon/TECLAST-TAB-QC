package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.usb.tester

import android.content.Context
import android.content.pm.PackageManager
import com.teclast_korea.teclast_qc_application.domain.qc_result.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*

// checkUsbHostModeAvailability test
fun usbTest2(state: TestResultState,
             onEvent: (TestResultEvent) -> Unit, context: Context): String {
    return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_USB_HOST)) {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(state = state, onEvent = onEvent, "USB TEST 2", "Success", Date().toString())
        onEvent(TestResultEvent.SaveTestResult)
        "Usb TEST : Success : USB Host Mode on : Card reader is available to use."


    } else {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(state = state, onEvent = onEvent, "USB TEST 2", "Fail", Date().toString())
        onEvent(TestResultEvent.SaveTestResult)
        "Usb TEST : Fail : USB Host Mode off : Card reader is not available to use."
    }
}
