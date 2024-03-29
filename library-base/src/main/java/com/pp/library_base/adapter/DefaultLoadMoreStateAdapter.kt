package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.pp.library_ui.BR
import com.pp.library_ui.R
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadMoreBinding
import com.pp.library_ui.utils.*

class DefaultLoadMoreStateAdapter(
    private val lifecycle: Lifecycle,
    private var onErrorClickListener: OnErrorClickListener? = null,
    private val getStateViewType: (loadState: LoadState) -> Int = { 0 },
) :
    LoadStateAdapter<BindingHolder<ItemDefaultLoadMoreBinding>>() {

    init {

        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                onErrorClickListener = null
            }
        })
    }

    override fun getStateViewType(loadState: LoadState): Int {
        return getStateViewType.invoke(loadState)
    }

    override fun onBindViewHolder(
        holder: BindingHolder<ItemDefaultLoadMoreBinding>,
        loadState: LoadState,
    ) {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())
        if (loadState is LoadState.Loading) {
//
//            holder.binding.loading.ivLoading1.starAnimator(lifecycle, R.animator.animator_loading1)
//            holder.binding.loading.ivLoading2.starAnimator(lifecycle, R.animator.animator_loading2)
//            holder.binding.loading.ivLoading3.starAnimator(lifecycle, R.animator.animator_loading3)
//            holder.binding.loading.ivLoading4.starAnimator(lifecycle, R.animator.animator_loading4)

        } else {
            holder.binding.loading.ivLoading1.animation?.cancel()
            holder.binding.loading.ivLoading2.animation?.cancel()
            holder.binding.loading.ivLoading3.animation?.cancel()
            holder.binding.loading.ivLoading4.animation?.cancel()
            holder.binding.loading.root.visibility = View.GONE
        }

        holder.binding.loadError.root.visibility =
            if (loadState is LoadState.Error) View.VISIBLE else View.GONE
        // 错误重试
        holder.binding.loadError.root.setOnClickListener {
            onErrorClickListener?.onErrorCLick((loadState as LoadState.Error).error)
        }

        holder.binding.loadDataEmpty.root.visibility =
            if (loadState is LoadState.NotLoading && loadState.endOfPaginationReached) View.VISIBLE else View.GONE

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): BindingHolder<ItemDefaultLoadMoreBinding> {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())
        return BindingHolder(
            ItemDefaultLoadMoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
//        Log.e("DefaultLoadStateAdapter", "displayLoadStateAsItem:   ${loadState.toString()}")
        // 父类默认实现: loading 或者 err 显示
        return super.displayLoadStateAsItem(loadState)
                // no data: not loading 并且 已到底部 (endOfPaginationReached) 显示
                || (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }

    override fun onViewAttachedToWindow(holder: BindingHolder<ItemDefaultLoadMoreBinding>) {
        val lifecycleOwner = ViewTreeLifecycleOwner.get(holder.binding.root)
        holder.binding.lifecycleOwner = lifecycleOwner

        val appTheme = ViewTreeAppThemeViewModel.get(holder.binding.root)
        holder.binding.setVariable(BR.themeViewModel, appTheme)
    }


}