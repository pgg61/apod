package com.samclub.astronomypicture.data.network

import com.samclub.astronomypicture.BuildConfig
import com.samclub.astronomypicture.data.network.interceptor.APIKeyInterceptor
import com.samclub.astronomypicture.data.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkServiceClient {
    private fun getApiInstance(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiService {
        val url = BuildConfig.BASE_URL
        val okkHttpclient = OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(APIKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getNetworkService(networkConnectionInterceptor: NetworkConnectionInterceptor) = getApiInstance(networkConnectionInterceptor)
}