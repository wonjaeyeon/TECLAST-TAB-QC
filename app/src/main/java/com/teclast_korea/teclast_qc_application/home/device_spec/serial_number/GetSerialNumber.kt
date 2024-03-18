package com.teclast_korea.teclast_qc_application.calendar.read_phone_state


fun getDeviceSerialNumber_v2(): String {
    var serial: String

    try {
        serial = android.os.Build.getSerial()
        if (serial != "" && serial != "unknown") return serial
    } catch (e: Exception) {
        //Log.e("getDeviceSerialNumber_v2", e.toString())
    }

    try {
        val c = Class.forName("android.os.SystemProperties")
        val get = c.getMethod("get", String::class.java)
        serial = get.invoke(c, "persist.sys.serialno") as String
        if (serial != "" && serial != "unknown") return serial
    } catch (e: Exception) {
        serial = ""
    }

    if(serial == ""){
        serial = "unknown"
    }

    return serial
}
