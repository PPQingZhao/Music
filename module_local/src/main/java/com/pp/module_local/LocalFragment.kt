package com.pp.module_local

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.base.ThemeFragment
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.RecyclerViewBindingAdapter
import com.pp.module_local.databinding.FragmentLocalBinding
import com.pp.module_local.databinding.ItemArrowForwardBinding
import com.pp.module_local.model.ItemLocalViewModel


@Route(path = RouterPath.Local.fragment_local)
class LocalFragment : ThemeFragment<FragmentLocalBinding, LocalViewModel>() {
    override val mBinding: FragmentLocalBinding by lazy {
        FragmentLocalBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<LocalViewModel> {
        return LocalViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initRecyclerView()
    }

    private val mAdapter by lazy {
        object :
            RecyclerViewBindingAdapter<ItemArrowForwardBinding, ItemLocalViewModel, ItemLocalViewModel>() {
            override fun createViewModel(
                binding: ItemArrowForwardBinding,
                item: ItemLocalViewModel?,
                cacheItemViewModel: ItemLocalViewModel?,
            ): ItemLocalViewModel? {
                return item
            }

            override fun onCreateBinding(
                parent: ViewGroup,
                viewType: Int,
            ): ItemArrowForwardBinding {
                return ItemArrowForwardBinding.inflate(layoutInflater, parent, false)
            }
        }
    }

    private fun getItems(): List<ItemLocalViewModel> {
        val dataList = mutableListOf<ItemLocalViewModel>()
        dataList.add(
            ItemLocalViewModel(
                com.pp.library_theme.R.drawable.ic_all_musics,
                R.string.all_musics
            )
        )
        dataList.add(
            ItemLocalViewModel(
                com.pp.library_theme.R.drawable.ic_download,
                R.string.download
            )
        )
        dataList.add(
            ItemLocalViewModel(
                com.pp.library_theme.R.drawable.ic_recently_played,
                R.string.recently_played
            )
        )
        dataList.add(ItemLocalViewModel(com.pp.library_theme.R.drawable.ic_liked, R.string.liked))
        dataList.add(ItemLocalViewModel(com.pp.library_theme.R.drawable.ic_theme, R.string.theme))
        return dataList
    }

    private fun initRecyclerView() {
        mBinding.recyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerview.adapter = mAdapter
    }

    override fun onFirstResume() {
        mAdapter.setDataList(getItems())
    }

    private fun initView() {

        mBinding.ivHead.setOnClickListener {
            val transitionAnimation =
                ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
            ARouter.getInstance()
                .build(RouterPath.User.activity_login)
                .withOptionsCompat(transitionAnimation)
                .navigation(requireActivity(), 0)
        }
    }
}