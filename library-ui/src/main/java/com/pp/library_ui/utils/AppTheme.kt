package com.pp.library_ui.utils

import android.R
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData

class AppTheme : Theme {

    val windowBackground = MutableLiveData<Drawable?>()
    val colorPrimary = MutableLiveData<ColorStateList>()
    val colorAccent = MutableLiveData<ColorStateList>()
    val textColor = MutableLiveData<ColorStateList>()
    val textColorSecondary = MutableLiveData<ColorStateList>()
    val textColorHint = MutableLiveData<ColorStateList>()
    val editTextColor = MutableLiveData<ColorStateList>()
    val themeTint = MutableLiveData<ColorStateList>()
    val indicatorNormalColor = MutableLiveData<ColorStateList>()
    val indicatorSelectedColor = MutableLiveData<ColorStateList>()
    val dividerColor = MutableLiveData<ColorStateList>()
    val progressTint = MutableLiveData<ColorStateList>()
    val secondaryProgressTint = MutableLiveData<ColorStateList>()

    @SuppressLint("ResourceType", "Recycle")
    override fun setTheme(theme: Resources.Theme) {

        val drawableAttrMap = mapOf(windowBackground to R.attr.windowBackground)

        val colorAttrMap = mapOf(
            themeTint to com.pp.library_ui.R.attr.themeTint,
            colorPrimary to R.attr.colorPrimary,
            colorAccent to R.attr.colorAccent,
            textColor to R.attr.textColor,
            textColorSecondary to R.attr.textColorSecondary,
            textColorHint to R.attr.textColorHint,
            editTextColor to R.attr.editTextColor,
            indicatorNormalColor to com.pp.library_ui.R.attr.indicatorNormalColor,
            indicatorSelectedColor to com.pp.library_ui.R.attr.indicatorSelectedColor,
            dividerColor to com.pp.library_ui.R.attr.dividerColor,
            progressTint to R.attr.progressTint,
            secondaryProgressTint to R.attr.secondaryProgressTint,
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