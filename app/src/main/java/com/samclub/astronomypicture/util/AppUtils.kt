package com.samclub.astronomypicture.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class AppUtils {

    @SuppressLint("SimpleDateFormat")
    fun isFetchFromCloudNeeded(apodRetrievedFortoday: String?): Boolean {
        apodRetrievedFortoday?.let {
            val currentDateTime =  SimpleDateFormat(AppConstants.DATE_FORMAT, Locale.US).format(Date())
            val currentDateToday = SimpleDateFormat(AppConstants.DATE_FORMAT).parse(currentDateTime)
            val apodRetrievedDate =
                SimpleDateFormat(AppConstants.DATE_FORMAT).parse(it)

            if (currentDateToday.before(apodRetrievedDate))
                return true
        }
        return false
    }
}