package com.pp.module_local.model

import android.view.View
import androidx.databinding.ObservableInt

open class ItemArrowForwardViewModel {
    val icon = ObservableInt()
    val content = ObservableInt()

    open fun onItemClick(v: View) {

    }
}