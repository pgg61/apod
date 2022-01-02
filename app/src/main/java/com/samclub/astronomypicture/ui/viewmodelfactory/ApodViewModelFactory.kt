package com.samclub.astronomypicture.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samclub.astronomypicture.data.repositories.ApodRepository
import com.samclub.astronomypicture.ui.viewmodel.ApodViewModel

class ApodViewModelFactory (
    private val repository: ApodRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ApodViewModel(repository) as T
    }
}