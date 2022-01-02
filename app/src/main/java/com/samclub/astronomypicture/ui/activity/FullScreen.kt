package com.samclub.astronomypicture.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.samclub.astronomypicture.R
import com.samclub.astronomypicture.databinding.ActivityApodFullBinding
import com.samclub.astronomypicture.ui.uiutil.UIUtils
import com.samclub.astronomypicture.util.AppConstants.APOD_URL


class FullScreen : AppCompatActivity() {
    private lateinit var dataBind: ActivityApodFullBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_apod_full)

        // check intent is null or not
        if (intent != null) {
            val url = intent.getStringExtra(APOD_URL)
            UIUtils.setGlideImagefullScreen(
                dataBind.imageApod,
                url!!
            )
        }
    }
}