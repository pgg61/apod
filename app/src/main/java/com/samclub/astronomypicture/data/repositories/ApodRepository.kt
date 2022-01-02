package com.samclub.astronomypicture.data.repositories

import com.samclub.astronomypicture.data.local.dao.SharedPref
import com.samclub.astronomypicture.data.model.ApodResponse
import com.samclub.astronomypicture.data.network.ApiHelper
import com.samclub.astronomypicture.util.AppConstants

class ApodRepository (
    private val apiHelper: ApiHelper,
    private val db: SharedPref
) {
    suspend fun fetchApod() = apiHelper.getApod()

    fun fetchApodDetails(): ApodResponse? {
        val obj = db.getObject(AppConstants.APOD_KEY, ApodResponse::class.java)
        return if(obj!=null)
            obj as ApodResponse
        else null
    }

    fun saveApodDetails(apodResponse: ApodResponse){
        db.putObject(AppConstants.APOD_KEY,apodResponse)
    }
}