package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.gpu.tester

import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.TestResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLContext

suspend fun gpuTest1(state: TestResultState,
                     onEvent: (TestResultEvent) -> Unit,): String {
    return withContext(Dispatchers.Default) {
        try {
            val egl = EGLContext.getEGL() as EGL10
            val display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)

            val version = IntArray(2)
            egl.eglInitialize(display, version)

            val configAttribs = intArrayOf(
                EGL10.EGL_SURFACE_TYPE, EGL10.EGL_WINDOW_BIT,
                EGL10.EGL_RED_SIZE, 8,
                EGL10.EGL_GREEN_SIZE, 8,
                EGL10.EGL_BLUE_SIZE, 8,
                EGL10.EGL_ALPHA_SIZE, 8,
                EGL10.EGL_DEPTH_SIZE, 16,
                EGL10.EGL_STENCIL_SIZE, 0,
                EGL10.EGL_NONE
            )

            val configs = arrayOfNulls<EGLConfig>(1)
            val numConfig = IntArray(1)
            egl.eglChooseConfig(display, configAttribs, configs, 1, numConfig)

            val contextAttribs = intArrayOf(EGL10.EGL_NONE)
            egl.eglCreateContext(display, configs[0], EGL10.EGL_NO_CONTEXT, contextAttribs)

            val error = egl.eglGetError()
            if (error != EGL10.EGL_SUCCESS) {

                AddTestResult(state = state, onEvent = onEvent, "GPU TEST 1", "Fail", Date().toString())
                "GPU TEST 1: Fail. EGL error code $error"
            } else {
                AddTestResult(state = state, onEvent = onEvent, "GPU TEST 1", "Success", Date().toString())
                "GPU TEST 1: Success"
            }
        } catch (error: Exception) {
            AddTestResult(state = state, onEvent = onEvent, "GPU TEST 1", "Fail", Date().toString())
            "GPU TEST 1: Fail. Error : ${error.message}"
        }
    }
}
