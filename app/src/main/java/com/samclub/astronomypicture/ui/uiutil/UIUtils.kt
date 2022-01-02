package com.samclub.astronomypicture.ui.uiutil

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.samclub.astronomypicture.R

object UIUtils {
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
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .fitCenter()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .thumbnail(0.75f)
            .into(image)
    }

    fun setGlideImagefullScreen(image: ImageView, url: String) {
        Glide.with(image).load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .fitCenter()
            .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .thumbnail(0.75f)
            .into(image)
    }



}