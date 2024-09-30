package com.teclast_korea.teclast_qc_application.ui.device_tester.sub_screen.test_filter

import android.os.Build
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun filterTestMode(): List<Pair<String, ImageVector>> {
    var device_firmware_name = Build.DISPLAY

    var test_mode_list = listOf(
        Pair("Standard Mode", Icons.AutoMirrored.Filled.Assignment),
        Pair("Fast Mode", Icons.Filled.ElectricBolt)
    )

    if (device_firmware_name.contains("torder") or device_firmware_name.contains("t_order")) {
        test_mode_list = test_mode_list + Pair("T-Order Mode", Icons.Filled.SmartDisplay)
    }

    if (device_firmware_name.contains("scspro")) {
        test_mode_list = test_mode_list + Pair("SCSPRO Mode", Icons.Filled.SmartDisplay)
    }
    //TODO : 이 로직은 현재 로컬 환경에서만 임의로 만든 로직이기에 업데이트가 불가능하고 다소 부족하다.
    // 나중에 서버에 Request Model.display를 보내고, Response로 TestMode를 받아서 추가하는 방식으로 변경해야 한다.

    return test_mode_list
}