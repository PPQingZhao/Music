package com.pp.library_ui.databinding

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:background")
    fun setImageResource(iv: View, drawable: Drawable?) {
//        Log.e("TAG", "res: $res")
        iv.background = drawable
    }

    @SuppressLint("ResourceType")
    @JvmStatic
    @androidx.databinding.BindingAdapter("android:text")
    fun setText(view: TextView, @StringRes resId: Int) {
//        Log.e("TAG", "res: $res")
        if (resId > 0) {
            view.setText(resId)
        } else {
            view.text = ""
        }
    }

}