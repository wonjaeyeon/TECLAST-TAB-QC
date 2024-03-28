package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.copyright_list

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.FileOutputStream
import java.io.InputStreamReader

class LicenseJsonGenerator(private val context: Context) {

//    fun generateJsonFile() {
//        val licenseInfos = parseLicenseMetadata()
//        val json = Gson().toJson(licenseInfos)
//        writeToFile(json, "open_source_licenses.json")
//    }

    fun generateJson(): String {
        val licenseInfos = parseLicenseMetadata()
        val json = Gson().toJson(licenseInfos)
        Log.i("LicenseJsonGenerator", "Generated JSON: $json")
        return json
    }

    private fun parseLicenseMetadata(): List<OpenSourceLicenseInfo> {
        val inputStream = context.resources.openRawResource(
            context.resources.getIdentifier(
                "third_party_license_metadata", "raw", context.packageName
            )
        )
        val licenseDetails = InputStreamReader(inputStream).readLines()

        val licenseInfos = mutableListOf<OpenSourceLicenseInfo>()
        licenseDetails.forEachIndexed { index, line ->
            val regex = "(\\d+):(\\d+) (.+)".toRegex()
            regex.find(line)?.let { matchResult ->
                val (start, end, libraryName) = matchResult.destructured
                // Assuming you have a method to fetch the actual license text based on range
                val licenseInfo = getLicenseInfo(index, libraryName)
                licenseInfos.add(licenseInfo)
            }
        }

        return licenseInfos
    }


    private fun writeToFile(jsonString: String, fileName: String) {
        val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        fos.write(jsonString.toByteArray())
        fos.close()
    }


    fun getLicenseInfo(index: Int, libraryName: String): OpenSourceLicenseInfo {
        val (license, copyright, link) =
            when {
                libraryName.contains("AndroidX", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright (c) 2005-2011, The Android Open Source Project",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("org.jetbrains.kotlin", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.",
                    "https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt"
                )

                libraryName.contains("org.jetbrains:annotations", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2000-2016 JetBrains s.r.o.",
                    "https://github.com/JetBrains/java-annotations/blob/master/LICENSE.txt"
                )

                libraryName.contains("io.ktor", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2000-2023 JetBrains s.r.o.",
                    "https://github.com/ktorio/ktor/blob/main/LICENSE"
                )

                libraryName.contains("com.google.android.gms", ignoreCase = true) -> Triple(
                    "Android Software Development Kit License Agreement",
                    "Copyright Google LLC",
                    "https://developer.android.com/studio/terms"
                )


                libraryName.contains("Animal Sniffer", ignoreCase = true) -> Triple(
                    "MIT License",
                    "Copyright (c) 2008 Kohsuke Kawaguchi and codehaus.org",
                    "https://www.mojohaus.org/animal-sniffer/licenses.html"
                )

                libraryName.contains("Checker Framework Annotations", ignoreCase = true) -> Triple(
                    "MIT License",
                    "Copyright (c) 2005-2011, The Android Open Source Project",
                    "https://github.com/typetools/annotation-tools/blob/master/LICENSE.txt"
                )

                libraryName.contains("com.google.maps.android", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2010 Google LLC",
                    "https://github.com/googlemaps/android-maps-compose/blob/main/LICENSE"
                )

                libraryName.contains("Google Play Services", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2010 Google LLC",
                    "https://developers.google.com/android/guides/overview"
                )

                libraryName.contains("Error Prone", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2018 Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("Guava", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright (C) 2008 The Guava Authors",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("J2ObjC", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2014 Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("JSR", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2013 Oracle America, Inc. All rights reserved.",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("JSpecify", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "JSpecify Contributors",
                    "https://github.com/jspecify/jspecify/blob/main/LICENSE"
                )

                libraryName.contains("JsInterop Annotations", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2020 Google LLC",
                    "https://github.com/google/jsinterop-annotations/blob/master/LICENSE"
                )

                libraryName.contains("Kotlin", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("SafeParcelable library", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "https://kotlinlang.org/docs/reference/coroutines-overview.html"
                )

                libraryName.contains("apksig", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright (c) 2016, The Android Open Source Project",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("junit", ignoreCase = true) -> Triple(
                    "Eclipse Public License 1.0",
                    "Copyright © 2002-2021 JUnit",
                    "https://junit.org/junit4/license.html"
                )

                libraryName.contains("com.google.ar.sceneform", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "https://github.com/google-ar/sceneform-android-sdk/blob/master/LICENSE"
                )

                libraryName.contains("javax.inject:javax.inject", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("com.google.accompanist", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("Google Auto", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2013 Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("Tink", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("Dagger", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("FlatBuffers", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("Protocol Buffers for Java", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("archive_patcher", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("gsfclient", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Google LLC",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("com.squareup", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Square, Inc.",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("com.squareup", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright Square, Inc.",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("io.github.aakira:napier-android", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright 2019 AAkira",
                    "http://www.apache.org/licenses/LICENSE-2.0"
                )

                libraryName.contains("p2putils", ignoreCase = true) -> Triple(
                    "Apache License 2.0",
                    "Copyright (c) 2016, The Android Open Source Project",
                    "http://www.apache.org/licenses/LICENSE-2.0.txt"
                )

                else -> Triple("Unknown License", "Unknown Copyright", "")
            }

        return OpenSourceLicenseInfo(
            id = index,
            Name = libraryName,
            License = license,
            copyright = copyright,
            link = link
        )
    }
}