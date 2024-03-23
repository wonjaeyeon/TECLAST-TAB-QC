package com.teclast_korea.teclast_qc_application.home.device_spec

import android.opengl.GLES20

fun getGpuInfo(): String {
    val gpuInfo = StringBuilder()

    // Run these in an OpenGL ES context
    val renderer = GLES20.glGetString(GLES20.GL_RENDERER) ?: "Unknown"
    val vendor = GLES20.glGetString(GLES20.GL_VENDOR) ?: "Unknown"
    val version = GLES20.glGetString(GLES20.GL_VERSION) ?: "Unknown"
    val extensions = GLES20.glGetString(GLES20.GL_EXTENSIONS) ?: "Unknown"

    gpuInfo.append("Renderer: ").append(renderer).append("\n")
    gpuInfo.append("Vendor: ").append(vendor).append("\n")
    gpuInfo.append("Version: ").append(version).append("\n")
    gpuInfo.append("Extensions: ").append(extensions)

    return gpuInfo.toString()
}

