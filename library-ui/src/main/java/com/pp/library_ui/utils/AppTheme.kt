package com.pp.library_ui.utils

import android.R
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData

class AppTheme : Theme {

    val windowBackground = MutableLiveData<Drawable?>()
    val contentBackground = MutableLiveData<Drawable?>()
    val colorPrimary = MutableLiveData<ColorStateList>()
    val colorAccent = MutableLiveData<ColorStateList>()
    val textColor = MutableLiveData<ColorStateList>()
    val textColorSecondary = MutableLiveData<ColorStateList>()
    val textColorHint = MutableLiveData<ColorStateList>()
    val editTextColor = MutableLiveData<ColorStateList>()
    val indicatorNormalColor = MutableLiveData<ColorStateList>()
    val indicatorSelectedColor = MutableLiveData<ColorStateList>()
    val progressTint = MutableLiveData<ColorStateList>()
    val secondaryProgressTint = MutableLiveData<ColorStateList>()
    val colorControlActivated = MutableLiveData<ColorStateList>()
    val colorButtonNormal = MutableLiveData<ColorStateList>()

    @SuppressLint("ResourceType", "Recycle")
    override fun setTheme(theme: Resources.Theme) {

        val drawableAttrMap = mapOf(
            windowBackground to R.attr.windowBackground,
            contentBackground to com.pp.library_ui.R.attr.contentBackground
        )

        val colorAttrMap = mapOf(
            colorPrimary to R.attr.colorPrimary,
            colorAccent to R.attr.colorAccent,
            textColor to R.attr.textColor,
            textColorSecondary to R.attr.textColorSecondary,
            textColorHint to R.attr.textColorHint,
            editTextColor to R.attr.editTextColor,
            indicatorNormalColor to com.pp.library_ui.R.attr.indicatorNormalColor,
            indicatorSelectedColor to com.pp.library_ui.R.attr.indicatorSelectedColor,
            progressTint to R.attr.progressTint,
            secondaryProgressTint to R.attr.secondaryProgressTint,
            colorControlActivated to R.attr.colorControlActivated,
            colorButtonNormal to R.attr.colorButtonNormal,
        )


        val attrMap = drawableAttrMap + colorAttrMap

        val attrArr = attrMap.values.toIntArray()
        val typedArray = theme.obtainStyledAttributes(attrArr)

        val drawableStartIndex = 0
        drawableAttrMap.keys.forEachIndexed { index, mutableLiveData ->
            try {
                mutableLiveData.value = typedArray.getDrawable(drawableStartIndex + index)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val colorStartIndex = drawableAttrMap.size
        colorAttrMap.keys.forEachIndexed { index, mutableLiveData ->
            try {
                mutableLiveData.value = typedArray.getColorStateList(colorStartIndex + index)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        typedArray.recycle()
    }
}