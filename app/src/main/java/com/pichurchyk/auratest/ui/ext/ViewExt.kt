package com.pichurchyk.auratest.ui.ext

import android.view.View

fun View.setVisibility(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}