package com.example.teclast_qc_application

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.ChecklistRtl
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.teclast_qc_application.ui.theme.MyApplicationTheme
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // 권한이 허용되었을 때 수행할 작업
            } else {
                // 권한이 거부되었을 때 수행할 작업
                throw Error("Permission denied")
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                MainScreenView(context = this)
            }

            requestCameraPermission()
            //requestReadExternalStoragePermission()
            //isDeviceRooted()
            //requestReadExternalStoragePermission()
            //requestReadLogsPermission()
        }


    }
    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                // 권한이 이미 허용되어 있는 경우
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) -> {
                // 권한 요청에 대한 설명이 필요한 경우
                // 사용자에게 권한 요청 이유를 설명한 후 권한 요청을 수행하세요
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                // 권한 요청
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
    //read external storage 권한 요청
//    private fun requestReadExternalStoragePermission() {
//        when {
//            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
//                // 권한이 이미 허용되어 있는 경우
//                println("read external storage granted")
//            }
//            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) -> {
//                // 권한 요청에 대한 설명이 필요한 경우
//                // 사용자에게 권한 요청 이유를 설명한 후 권한 요청을 수행하세요
//                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//            }
//            else -> {
//                // 권한 요청
//                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//                println("read external storage granted")
//            }
//        }
//    }
    //read
//    private fun requestReadLogsPermission() {
//        when {
//            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_LOGS) == PackageManager.PERMISSION_GRANTED -> {
//                // Permission is already granted
//            }
//            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_LOGS) -> {
//                // An explanation is needed for the permission request
//                // Explain the reason for the permission request to the user, and then request the permission
//                requestPermissionLauncher.launch(Manifest.permission.READ_LOGS)
//            }
//            else -> {
//                // Request permission
//                requestPermissionLauncher.launch(Manifest.permission.READ_LOGS)
//            }
//        }
//    }

}



@RequiresApi(34)
@Composable
fun MainScreenView(context: MainActivity) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(Modifier.padding(it)){
            navigationGraph(context = context,navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Test,
        BottomNavItem.Analysis,
        BottomNavItem.Settings
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.title)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(
    val title: Int,
    val icon: ImageVector,
    val screenRoute: String
) {
    object Home : BottomNavItem(
        R.string.text_calendar,
        Icons.Filled.Home,
        STATEBOARD
    )

    object Test : BottomNavItem(
        R.string.text_timeline,
        Icons.Filled.ChecklistRtl,
        DEVICETESTER
    )

    object Analysis : BottomNavItem(
        R.string.text_analysis,
        Icons.Filled.Assignment,
        LOGREPORT
    )

    object Settings : BottomNavItem(
        R.string.text_settings,
        Icons.Filled.Settings,
        SETTINGS
    )
}





@Composable
fun DefaultPreview(context: MainActivity) {
    MyApplicationTheme {
        CalendarScreen2(context = context)
    }
}


fun isDeviceRooted(): Boolean {
    val command = "echo \"checking root\""
    return try {
        val process = Runtime.getRuntime().exec(arrayOf("su", "-c", command))
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val output = reader.readLine()
        reader.close()
        process.waitFor()
        output == "checking root"

    } catch (e: Exception) {
        Log.e("isDeviceRooted", "Error checking for root", e)
        false
    }
}