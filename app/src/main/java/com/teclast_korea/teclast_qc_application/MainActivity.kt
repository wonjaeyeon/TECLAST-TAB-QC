package com.teclast_korea.teclast_qc_application

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.ChecklistRtl
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.TestResultDatabase
import com.teclast_korea.teclast_qc_application.ui.router.*
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultViewModel
import com.teclast_korea.teclast_qc_application.ui.theme.MyApplicationTheme
import kotlin.reflect.KFunction1

private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

private fun foregroundPermissionApproved(context: Context): Boolean {
    val writePermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val readPermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    )

    return writePermissionFlag && readPermissionFlag
}

private fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context = context)
    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }
}

//check fast_mode_implement branch is well-made
// check if I changed all right
class MainActivity : ComponentActivity() {
    val VolumeUpPressed = mutableStateOf(false)
    val VolumeDownPressed = mutableStateOf(false)
    val IsDarkTheme = mutableStateOf(true)
    val IsBottomBarVisible = mutableStateOf(true)

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // 권한이 허용되었을 때 수행할 작업
            } else {
                // 권한이 거부되었을 때 수행할 작업
                //throw Error("Permission denied")
            }
        }

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TestResultDatabase::class.java,
            "testResults.db"
        ).build()
    }
    private val viewModel by viewModels<TestResultViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TestResultViewModel(db.dao) as T
                }
            }
        }
    )


    // Inside your activity class
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                // Do something when the volume up button is pressed
                Log.i("MainActivity", "Volume up pressed")
                VolumeUpPressed.value = true
                true
            }

            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                // Do something when the volume down button is pressed
                Log.i("MainActivity", "Volume down pressed")
                VolumeDownPressed.value = true
                true
            }

            else -> {
                super.onKeyDown(keyCode, event)
            }
        }
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_SETTINGS)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // when deleting Database, uncomment below
        // deleteDatabase("testResult.db")

        setContent {
            val context = LocalContext.current
            requestForegroundPermission(context)

            MyApplicationTheme(darkTheme = IsDarkTheme.value) {
                val state by viewModel.state.collectAsState()
                MainScreenView(
                    context = this,
                    state = state,
                    onEvent = viewModel::onEvent,
                    volumeUpPressed = VolumeUpPressed,
                    volumeDownPressed = VolumeDownPressed,
                    darkTheme = IsDarkTheme,
                    isBottomBarVisible = IsBottomBarVisible
                )
            }

            requestCameraPermission()
            requestReadPhonePermission()
            //requestReadExternalStoragePermission()
            //isDeviceRooted()
            //requestReadExternalStoragePermission()
            //requestReadLogsPermission()
        }


    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
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

    private fun requestReadPhonePermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // 권한이 이미 허용되어 있는 경우
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE) -> {
                // 권한 요청에 대한 설명이 필요한 경우
                // 사용자에게 권한 요청 이유를 설명한 후 권한 요청을 수행하세요
                requestPermissionLauncher.launch(Manifest.permission.READ_PHONE_STATE)
            }

            else -> {
                // 권한 요청
                requestPermissionLauncher.launch(Manifest.permission.READ_PHONE_STATE)
            }
        }
    }
}


@RequiresApi(34)
@Composable
fun MainScreenView(
    context: MainActivity,
    state: TestResultState,
    onEvent: KFunction1<TestResultEvent, Unit>,
    volumeUpPressed: MutableState<Boolean>,
    volumeDownPressed: MutableState<Boolean>,
    darkTheme: MutableState<Boolean>,
    isBottomBarVisible: MutableState<Boolean>
) {
    val navController = rememberNavController()
    Scaffold(

        bottomBar = {
            if (isBottomBarVisible.value)
                BottomNavigation(navController = navController)
            else {

            }
        }
    ) {


        Box(Modifier.padding(it)) {

            navigationGraph(
                navController = navController,
                context = context,
                state = state,
                onEvent = onEvent,
                volumeUpPressed = volumeUpPressed,
                volumeDownPressed = volumeDownPressed,
                darkTheme = darkTheme,
                isBottomBarVisible = isBottomBarVisible,
                onExitApp = { context.finishAndRemoveTask() })
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
        backgroundColor = MaterialTheme.colors.onPrimary,
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
        Icons.AutoMirrored.Filled.Assignment,
        LOGREPORT
    )

    object Settings : BottomNavItem(
        R.string.text_settings,
        Icons.Filled.Settings,
        SETTINGS
    )
}
