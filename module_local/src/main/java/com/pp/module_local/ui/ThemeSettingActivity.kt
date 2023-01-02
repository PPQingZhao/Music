package com.pp.module_local.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.datastore.PreferenceTheme
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.RecyclerViewBindingAdapter
import com.pp.module_local.databinding.ActivityThemeSettingBinding
import com.pp.module_local.databinding.ItemThemeSettingBinding
import com.pp.module_local.model.ItemPreferenceThemeSettingViewModel

@Route(path = RouterPath.Local.activity_them_setting)
class ThemeSettingActivity : ThemeActivity<ActivityThemeSettingBinding, ThemeSettingViewModel>() {
    override val mBinding: ActivityThemeSettingBinding by lazy {
        ActivityThemeSettingBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ThemeSettingViewModel> {
        return ThemeSettingViewModel::class.java
    }

    fun onBack(v: View) {
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    private val mAdapter by lazy {
        object :
            RecyclerViewBindingAdapter<ItemThemeSettingBinding, ItemPreferenceThemeSettingViewModel, ItemPreferenceThemeSettingViewModel>() {
            override fun createViewModel(
                binding: ItemThemeSettingBinding,
                item: ItemPreferenceThemeSettingViewModel?,
                cacheItemViewModel: ItemPreferenceThemeSettingViewModel?,
            ): ItemPreferenceThemeSettingViewModel? {
                return item
            }

            override fun onCreateBinding(
                parent: ViewGroup,
                viewType: Int,
            ): ItemThemeSettingBinding {
                return ItemThemeSettingBinding.inflate(layoutInflater, parent, false)
            }
        }
    }

    private fun initRecyclerView() {
        mBinding.recyclerview.layoutManager = GridLayoutManager(this, 3)
        mBinding.recyclerview.adapter = mAdapter
        mAdapter.setDataList(getThemes())
    }

    private fun getThemes(): List<ItemPreferenceThemeSettingViewModel> {
        val dataList = mutableListOf<ItemPreferenceThemeSettingViewModel>()
        dataList.add(
            ItemPreferenceThemeSettingViewModel(
                resources.newTheme(),
                PreferenceTheme.SIMPLE_DEFAULT
            )
        )
        dataList.add(
            ItemPreferenceThemeSettingViewModel(
                resources.newTheme(),
                PreferenceTheme.SIMPLE_NIGHT
            )
        )
        dataList.add(
            ItemPreferenceThemeSettingViewModel(
                resources.newTheme(),
                PreferenceTheme.SIMPLE_BLUE
            )
        )

        dataList.onEach {
            lifecycle.addObserver(it)
        }
        return dataList
    }

}