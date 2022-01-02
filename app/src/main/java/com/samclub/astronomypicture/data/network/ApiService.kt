package com.samclub.astronomypicture.data.network

import com.samclub.astronomypicture.data.model.ApodResponse
import com.samclub.astronomypicture.data.network.ApiEndPointsConstants.APOD_URL
import retrofit2.http.GET

interface ApiService {
    @GET(APOD_URL)
    suspend fun findApod(): ApodResponse
}