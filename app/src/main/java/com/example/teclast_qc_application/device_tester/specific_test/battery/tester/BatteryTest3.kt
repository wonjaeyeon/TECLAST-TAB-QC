package com.example.teclast_qc_application.device_tester.specific_test.battery.tester

// checking the current battery voltage
//class MainViewModel(private val context: Context) : ViewModel() {
//    var batteryVoltage = mutableStateOf("")
//
//    init {
//        getBatteryVoltage()
//    }
//
//    private fun getBatteryVoltage() {
//        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
//        val voltage = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_VOLTAGE)
//
//        // Convert to volts (the value is in millivolts)
//        val volts = voltage / 1000.0
//        batteryVoltage.value = "${volts}V"
//    }
//}