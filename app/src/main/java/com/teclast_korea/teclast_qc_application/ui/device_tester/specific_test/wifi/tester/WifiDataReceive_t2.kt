package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.wifi.tester

//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//
//
//@Composable
//fun GetIpGeolocation() : String{
//    val (requestResult, setRequestResult) = remember { mutableStateOf<String>("") }
//
//    val requestParameters = mapOf("fields" to "city,regionName,country,lat,lon", "lang" to "fr")
//
//    // Launch the request in a separate coroutine so as not to block the main thread
//    LaunchedEffect(Unit) {
//        val response = get(
//            url = "http://ip-api.com/json/121.157.24.227",
//            params = requestParameters
//        )
//
//        // Update state with response
//        setRequestResult(response.text)
//    }
//
//    return requestResult
//}

//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.material.Text
//import khttp.get
//
//@Composable
//fun DisplayIpGeolocation() {
//    val (requestResult, setRequestResult) = remember { mutableStateOf<String>("") }
//
//    val requestParameters = mapOf("fields" to "city,regionName,country,lat,lon", "lang" to "fr")
//
//    // Launch the request in a separate coroutine so as not to block the main thread
//    LaunchedEffect(Unit) {
//        val response = khttp.get(
//            url = "http://ip-api.com/json/121.157.24.227",
//            params = requestParameters
//        )
//
//        // Update state with response
//        setRequestResult(response.text)
//    }
//
//    // Observe the state and display it
//    Text(requestResult)
//}

//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import io.ktor.client.*
//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*
//import io.ktor.client.request.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//
//suspend fun DisplayIpGeolocation() :String {
//    val (requestResult, setRequestResult) = remember { mutableStateOf<String>("") }
//    val requestParameters = mapOf("fields" to "city,regionName,country,lat,lon", "lang" to "fr")
//
//    // Initialize the Ktor client
//    val client = HttpClient {
//        install(JsonFeature) {
//            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
//                ignoreUnknownKeys = true
//            })
//        }
//    }
//
//    // Launch the request in a separate coroutine so as not to block the main thread
//    LaunchedEffect(Unit) {
//        try {
//            val response: String = withContext(Dispatchers.IO) {
//                client.get("http://ip-api.com/json/121.157.24.227") {
//                    parameter("fields", "city,regionName,country,lat,lon")
//                }
//            }
//            // Update state with response
//            setRequestResult(response)
//        } catch (e: Exception) {
//            // Handle exception
//            setRequestResult("Error: $e")
//        }
//    }
//
//    // Observe the state and display it
//    return requestResult
//}

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchIpGeolocation() :String {
    // Initialize the Ktor client
    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    var requestResult: String

    try {
        requestResult = withContext(Dispatchers.IO) {
            client.get("http://ip-api.com/json/211.105.221.149") {
                parameter("fields", "status,continent,city,regionName,country,lat,lon,timezone,isp,org,as,asname,query")
            }
        }
        // Handle exception
    } catch (e: Exception) {
        requestResult = "Error: $e"
    }
    // make requestResult /n for each :
    requestResult = requestResult.replace(",", ",\n")

    // Return the result
    return requestResult
}