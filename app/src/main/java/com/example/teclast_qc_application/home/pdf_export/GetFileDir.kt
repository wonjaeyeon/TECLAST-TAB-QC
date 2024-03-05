package com.example.teclast_qc_application.home.pdf_export

import android.content.Context
import java.io.File

fun getDirectory(context: Context): File {

    // 외부 미디어 디렉토리에 앱 이름으로 된 폴더를 생성하려고 시도합니다.
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, "QC_TEST_RESULT").apply { mkdirs() }
    }
    // 외부 디렉토리가 사용 가능하면 해당 디렉토리를, 그렇지 않으면 내부 파일 디렉토리를 반환합니다.
    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}