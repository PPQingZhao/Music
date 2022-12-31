package com.pp.module_local.model

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_router_service.services.RouterPath

class ItemLocalViewModel : ItemArrowForwardViewModel {
    private val navigationPath: String

    constructor(@DrawableRes icon: Int, @StringRes content: Int, navigationPath: String) : super() {
        super.icon.set(icon)
        super.content.set(content)

        this.navigationPath = navigationPath
    }

    override fun onItemClick(v: View) {

        (v.context as Activity).apply {

            RouterPath
            val transitionAnimation =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this)

            ARouter.getInstance().build(navigationPath)
                .withOptionsCompat(transitionAnimation)
                .navigation(this,0)
        }
    }

}