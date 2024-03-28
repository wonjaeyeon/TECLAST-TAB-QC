package com.teclast_korea.teclast_qc_application.home.device_spec

import java.net.Inet4Address
import java.net.Inet6Address
import java.net.NetworkInterface
import java.util.*

fun getIPv4Addresses(): String {
    val ipv4Addresses = mutableListOf<String>()
    try {
        val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (networkInterface in interfaces) {
            val inetAddresses = Collections.list(networkInterface.inetAddresses)
            for (inetAddress in inetAddresses) {
                if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                    inetAddress.hostAddress?.let { ipv4Addresses.add(it) }
                }
            }
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    if (ipv4Addresses.isEmpty()) {
        return "unknown"
    }

    return ipv4Addresses[0]
}

fun getIPv6Addresses(): String {
    val ipv6Addresses = mutableListOf<String>()
    try {
        val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (networkInterface in interfaces) {
            val inetAddresses = Collections.list(networkInterface.inetAddresses)
            for (inetAddress in inetAddresses) {
                if (!inetAddress.isLoopbackAddress && inetAddress is Inet6Address) {
                    // IPv6 주소에서 '%' 이후의 문자 제거
                    val address = inetAddress.hostAddress ?: return "unknown"
                    val validAddress = if (address.contains("%")) address.substring(0, address.indexOf('%')) else address
                    ipv6Addresses.add(validAddress)
                }
            }
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    if (ipv6Addresses.isEmpty()) {
        return "unknown"
    }

    return ipv6Addresses[0]
}