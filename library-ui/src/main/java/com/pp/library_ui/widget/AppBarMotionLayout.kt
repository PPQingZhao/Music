package com.pp.library_ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener

class AppBarMotionLayout : MotionLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val onOffsetChangedListener =
        OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            Log.e("TAG", "$verticalOffset")
//            Log.e("TAG", "${appBarLayout?.totalScrollRange}")
            appBarLayout?.apply {
                progress = -verticalOffset.toFloat() / appBarLayout.totalScrollRange
            }
        }

    private var appBarLayout: AppBarLayout? = null
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        var p = parent
        while (p != null) {
            if (p is AppBarLayout) {
                appBarLayout = p
                break
            }
            p = p.parent
        }

        appBarLayout?.apply {
            addOnOffsetChangedListener(onOffsetChangedListener)
        }
    }

    override fun onDetachedFromWindow() {
        appBarLayout?.apply {
            removeOnOffsetChangedListener(onOffsetChangedListener)
        }

        appBarLayout = null
        super.onDetachedFromWindow()
    }
}