package com.samclub.astronomypicture.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.samclub.astronomypicture.R
import com.samclub.astronomypicture.data.local.dao.SharedPref
import com.samclub.astronomypicture.data.network.ApiHelper
import com.samclub.astronomypicture.data.network.NetworkServiceClient
import com.samclub.astronomypicture.data.network.interceptor.NetworkConnectionInterceptor
import com.samclub.astronomypicture.data.repositories.ApodRepository
import com.samclub.astronomypicture.databinding.ActivityMainBinding
import com.samclub.astronomypicture.ui.uiutil.UIUtils
import com.samclub.astronomypicture.ui.uiutil.showToast
import com.samclub.astronomypicture.ui.uiutil.snackbar
import com.samclub.astronomypicture.ui.viewmodel.ApodViewModel
import com.samclub.astronomypicture.ui.viewmodelfactory.ApodViewModelFactory
import com.samclub.astronomypicture.util.AppConstants.APOD_URL
import com.samclub.astronomypicture.util.EventObserver
import com.samclub.astronomypicture.util.State


class MainActivity : AppCompatActivity() {
    private lateinit var dataBind: ActivityMainBinding
    private lateinit var viewModel: ApodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this, ApodViewModelFactory(
                ApodRepository(
                    ApiHelper(
                        NetworkServiceClient.getNetworkService(NetworkConnectionInterceptor(this))
                    ),
                    SharedPref.getPref(this)!!
                )
            )
        ).get(ApodViewModel::class.java)

        viewModel.fetchApod()

        observeAPICall()
    }

    private fun observeAPICall() {
        viewModel.apodLiveData.observe(this, EventObserver { state ->
            when (state) {
                is State.Loading -> {
                }
                is State.Success -> {
                    state.data.let { apodDetail ->
                        dataBind.apodTitle.text = apodDetail.title

                        UIUtils.setGlideImage(
                            dataBind.imageApod,
                            apodDetail.url!!
                        )
                        dataBind.apodDetails.text = apodDetail.explanation
                        if (!apodDetail.isApodofCurrentDate) {
                            dataBind.textApodDate.snackbar(getString(R.string.last_visited) + apodDetail.date)
                        }

                        dataBind.imageApod.setOnClickListener(View.OnClickListener {
                            val i = Intent(this, FullScreen::class.java)
                            i.putExtra(APOD_URL, apodDetail.hdurl)
                            startActivity(i)
                        })
                    }
                }
                is State.Error -> {
                    try {
                        val intResourceID = Integer.parseInt(state.message)
                        showToast(getString(intResourceID))
                    } catch (e: NumberFormatException) {
                        showToast(state.message)
                    }

                }
            }
        })

    }


}