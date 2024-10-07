package com.teclast_korea.teclast_qc_application.data.oss_license

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.teclast_korea.teclast_qc_application.data.oss_license.model.OSSLicenseInfo
import com.teclast_korea.teclast_qc_application.domain.oss_license.LicenseJsonGenerator


class OSSLicenseInfoRepository {
    @Composable
    fun getAllLicenseInfo(): List<OSSLicenseInfo> {

        // Dynamic data
        val generator = LicenseJsonGenerator(LocalContext.current)
        val dynamicLicensesJson = generator.generateJson()
        val dynamicLicenses = Gson().fromJson(dynamicLicensesJson, Array<OSSLicenseInfo>::class.java).toList()

        // Combine static and dynamic data
        return dynamicLicenses
    }
}