package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.oss_data

data class OpenSourceLicenseInfo(
    val id: Int,
    val Name: String,
    val License: String,
    val copyright: String,
    val link: String
)

class OSLRepository {
    fun getAllData(): List<OpenSourceLicenseInfo> {
        return listOf(
            OpenSourceLicenseInfo(
                id = 0,
                Name = "Android X",
                License = "Apache License 2.0",
                copyright = "2021 The Android Open Source Project",
                link = "https://developer.android.com/license"
            ),
            OpenSourceLicenseInfo(
                id = 1,
                Name = "Google Play Services",
                License = "Apache License 2.0",
                copyright = "2021 Google LLC",
                link = "https://developers.google.com/android/guides/overview"
            ),
            OpenSourceLicenseInfo(
                id = 2,
                Name = "Kotlin Coroutines",
                License = "Apache License 2.0",
                copyright = "2021 JetBrains",
                link = "https://kotlinlang.org/docs/reference/coroutines-overview.html"
            ),
            OpenSourceLicenseInfo(
                id = 3,
                Name = "CameraX",
                License = "Apache License 2.0",
                copyright = "2021 The Android Open Source Project",
                link = "https://developer.android.com/training/camerax"
            ),
            OpenSourceLicenseInfo(
                id = 4,
                Name = "Room Database",
                License = "Apache License 2.0",
                copyright = "2021 The Android Open Source Project",
                link = "https://developer.android.com/training/data-storage/room"
            ),
            OpenSourceLicenseInfo(
                id = 5,
                Name = "Ktor",
                License = "Apache License 2.0 (Server), MIT License (Client)",
                copyright = "2021 JetBrains",
                link = "https://ktor.io/"
            ),
            OpenSourceLicenseInfo(
                id = 6,
                Name = "JUnit",
                License = "Eclipse Public License 1.0",
                copyright = "2021 JUnit",
                link = "https://junit.org/junit4/"
            ),
            OpenSourceLicenseInfo(
                id = 7,
                Name = "Espresso",
                License = "Apache License 2.0",
                copyright = "2021 The Android Open Source Project",
                link = "https://developer.android.com/training/testing/espresso"
            )
        )
    }
}