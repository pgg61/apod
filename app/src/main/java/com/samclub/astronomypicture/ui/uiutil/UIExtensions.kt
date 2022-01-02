package com.samclub.astronomypicture.ui.uiutil

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.samclub.astronomypicture.R

// used for show a toast message
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// used for show snackbar
fun View.snackbar(message: String) {

    var snackBar: Snackbar?  = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    val snackBarView = snackBar?.view
    snackBarView?.setBackgroundColor(
        ContextCompat.getColor(context, R.color.color_light_blue)
    )
    val txtViewSnackBar =
        snackBarView?.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    txtViewSnackBar.textAlignment = View.TEXT_ALIGNMENT_CENTER
    txtViewSnackBar.setTextSize(
        TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.text_size_20sp)
    )
    snackBarView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    snackBar?.show()
}

//Extension function to show view
fun View.show() {
    visibility = View.VISIBLE
}

//Extension function to invisible view
fun View.invisible() {
    visibility = View.INVISIBLE
}

//Extension function to hide a view
fun View.hide() {
    visibility = View.GONE
}