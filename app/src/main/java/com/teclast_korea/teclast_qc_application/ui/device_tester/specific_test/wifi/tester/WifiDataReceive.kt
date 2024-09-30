package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.wifi.tester


//suspend fun getWifiDataReceive(context: Context, scope: CoroutineScope) : String {
//    var receivedMessage = ""
////    var receivedMessage = MutableLiveData<String>()
////
////    // Coroutine to handle the socket connection
////    CoroutineScope(Dispatchers.IO).launch {
////        val socket = Socket("172.30.1.2", 5000)  // Use '10.0.2.2' to connect to localhost in the emulator
////        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
////
////        while (true) {
////            val line = reader.readLine()
////            if (line != null) {
////                receivedMessage.postValue(line)
////            }
////        }
////    }
////    return receivedMessage.value.toString()
//
//    scope.launch {
//
//        val clientSocket = DatagramSocket(37020)
//        val receiveData = ByteArray(1024)
//
//        while (receivedMessage.length < 3) {
//            val receivePacket = DatagramPacket(receiveData, receiveData.size)
//            clientSocket.receive(receivePacket)
//            val sentence = String(receivePacket.data, 0, receivePacket.length)
//            receivedMessage = sentence
//
//        }
//
//    }
//    return receivedMessage
//}

// Make it as suspend function without return value
//@Composable
//fun getWifiDataReceive() : String {
//    var receivedMessage = ""
//    val clientSocket = DatagramSocket(37020)
//    val receiveData = ByteArray(1024)
//
//    while (receivedMessage.length < 3) {
//        val receivePacket = DatagramPacket(receiveData, receiveData.size)
//        clientSocket.receive(receivePacket)
//        val sentence = String(receivePacket.data, 0, receivePacket.length)
//        receivedMessage = sentence // This will update the state
//    }
//    return receivedMessage
//}

// Remove remember and CoroutineScope.launch from this function
//suspend fun getWifiDataReceive(context: Context): String {
//    val clientSocket = DatagramSocket(37020)
//    val receiveData = ByteArray(1024)
//    var sentence = "Listening..."
//
//    while (sentence.length < 100) {
//        val receivePacket = DatagramPacket(receiveData, receiveData.size)
//        clientSocket.receive(receivePacket)
//        sentence = String(receivePacket.data, 0, receivePacket.length)
//    }
//
//    return sentence
//}

//suspend fun getWifiDataReceive(context: Context): String = withContext(Dispatchers.IO) {
//    val clientSocket = DatagramSocket(37020)
//    val receiveData = ByteArray(1024)
//    var sentence = "Listening..."
//
//    while (sentence.length < 100) {
//        val receivePacket = DatagramPacket(receiveData, receiveData.size)
//        clientSocket.receive(receivePacket)
//        sentence = String(receivePacket.data, 0, receivePacket.length)
//    }
//
//    sentence
//}

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket

suspend fun startClient(): String = withContext(Dispatchers.IO) {
    val port = 37020  // The same port used by the server

    DatagramSocket(port).use { socket ->
        socket.use {
            val buffer = ByteArray(1024)
            val packet = DatagramPacket(buffer, buffer.size)
            socket.receive(packet)

            val receivedMessage = String(packet.data, 0, packet.length)
            println("Received message: $receivedMessage from: ${packet.address}:${packet.port}")
            return@withContext receivedMessage
        }
    }
}