package com.samclub.astronomypicture.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApodResponse(
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("explanation") var explanation: String? = null,
    @SerializedName("hdurl") var hdurl: String? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("service_version") var serviceVersion: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null,
    var isApodofCurrentDate: Boolean = true
)
