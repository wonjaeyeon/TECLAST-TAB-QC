package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.wifi.tester


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