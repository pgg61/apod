package com.samclub.astronomypicture.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.samclub.astronomypicture.R
import com.samclub.astronomypicture.data.excepions.ApiException
import com.samclub.astronomypicture.data.excepions.NoInternetException
import com.samclub.astronomypicture.data.model.ApodResponse
import com.samclub.astronomypicture.data.repositories.ApodRepository
import com.samclub.astronomypicture.util.AppUtils
import com.samclub.astronomypicture.util.Event
import com.samclub.astronomypicture.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApodViewModel(private val repository: ApodRepository) : ViewModel() {
    private val _apodLiveData =
        MutableLiveData<Event<State<ApodResponse>>>()
    val apodLiveData: LiveData<Event<State<ApodResponse>>>
        get() = _apodLiveData


    private lateinit var apodResponse: ApodResponse

    // fetching
    private fun fetchApodFromCloud() {
        _apodLiveData.postValue(Event(State.loading()))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                apodResponse =
                    repository.fetchApod()
                withContext(Dispatchers.Main) {
                    repository.saveApodDetails(apodResponse)
                    apodResponse.isApodofCurrentDate = true
                    _apodLiveData.postValue(
                        Event(
                            State.success(
                                apodResponse
                            )
                        )
                    )
                }
            } catch (e: ApiException) {
                withContext(Dispatchers.Main) {
                    _apodLiveData.postValue(
                        Event(
                            State.error(
                                e.message ?: "" + R.string.generic_error
                            )
                        )
                    )
                }
            } catch (e: NoInternetException) {
                withContext(Dispatchers.Main) {
                    _apodLiveData.postValue(
                        Event(
                            State.error(
                                e.message ?: "" + R.string.generic_error
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _apodLiveData.postValue(
                        Event(
                            State.error(
                                e.message ?: "" + R.string.generic_error
                            )
                        )
                    )
                }
            }
        }
    }

    // this was required to clear disk cache which is used to store retrieved image
    fun clearMemoryCache(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Glide.get(context).clearDiskCache()
        }
    }

    fun fetchApod() {
        val apodDetail = repository.fetchApodDetails()
        if (apodDetail != null) {
            if (AppUtils.isFetchFromCloudNeeded(apodDetail.date)) {
                fetchApodFromCloud()
            } else {
                _apodLiveData.postValue(
                    Event(
                        State.success(
                            apodDetail
                        )
                    )
                )
            }
        } else {
            fetchApodFromCloud()
        }
    }
}