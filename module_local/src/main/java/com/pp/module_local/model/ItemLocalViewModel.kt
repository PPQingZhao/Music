package com.pp.module_local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ItemLocalViewModel : ItemArrowForwardViewModel {
    constructor(@DrawableRes icon: Int, @StringRes content: Int) : super() {
        super.icon.set(icon)
        super.content.set(content)
    }
}