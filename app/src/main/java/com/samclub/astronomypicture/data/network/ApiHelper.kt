package com.samclub.astronomypicture.data.network

class ApiHelper(private val apiService: ApiService) {
    suspend fun getApod() = apiService.findApod()
}