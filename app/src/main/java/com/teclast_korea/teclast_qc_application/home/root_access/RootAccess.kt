package com.teclast_korea.teclast_qc_application.home.root_access

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader

fun executeWithRootPermission(command: String): BufferedReader {
    val process = Runtime.getRuntime().exec("su")
    val os = DataOutputStream(process.outputStream)

    os.writeBytes("$command\n")
    os.writeBytes("exit\n")
    os.flush()

    val reader = BufferedReader(InputStreamReader(process.inputStream))

    return reader
}