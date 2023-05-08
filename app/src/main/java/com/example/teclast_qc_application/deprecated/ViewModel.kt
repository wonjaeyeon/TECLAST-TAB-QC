package com.example.teclast_qc_application.deprecated

//class MainViewModel : ViewModel() {
//    private var volumeUpPressCount = 0
//
//    fun onVolumeUpPressed() {
//        volumeUpPressCount++
//
//        if (volumeUpPressCount == 3) {
//            volumeUpPressCount = 0
//            // Do something when the volume up button is pressed 3 times
//        }
//
//        viewModelScope.launch {
//            delay(1000) // Reset the counter after 1 second
//            volumeUpPressCount = 0
//        }
//    }
//}

//class MainViewModel : ViewModel() {
//    private val _triplePressTriggered = MutableLiveData(false)
//    val triplePressTriggered: LiveData<Boolean> = _triplePressTriggered
//
//    private var pressCount = 0
//
//    fun onVolumeUpPressed() {
//        pressCount++
//        if (pressCount == 3) {
//            _triplePressTriggered.value = true
//            pressCount = 0
//        }
//        viewModelScope.launch {
//            delay(500)
//            pressCount = 0
//        }
//    }
//}