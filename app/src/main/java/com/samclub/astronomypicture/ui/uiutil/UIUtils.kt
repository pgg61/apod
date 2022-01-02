package com.samclub.astronomypicture.ui.uiutil

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.samclub.astronomypicture.R

class UIUtils {
    fun showProgressBar(requireContext: Context) {
        if (!ProgressBar.getInstance().isDialogShowing()) {
            ProgressBar.getInstance().showProgress(requireContext, false)
        }
    }

    fun hideProgressBar() {
        ProgressBar.getInstance().dismissProgress()
    }

    fun setGlideImage(image: ImageView, url: String) {
        Glide.with(image).load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .thumbnail(0.75f)
            .into(image)
    }
}