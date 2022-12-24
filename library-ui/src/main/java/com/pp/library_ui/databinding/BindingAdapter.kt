package com.pp.library_ui.databinding

import android.graphics.drawable.Drawable
import android.view.View

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:background")
    fun setImageResource(iv: View, drawable: Drawable?) {
//        Log.e("TAG", "res: $res")
        iv.background = drawable
    }

}