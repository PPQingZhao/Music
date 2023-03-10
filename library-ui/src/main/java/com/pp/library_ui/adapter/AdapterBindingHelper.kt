package com.pp.library_ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.pp.library_ui.BR
import com.pp.library_ui.utils.ViewTreeAppThemeViewModel

abstract class AdapterBindingHelper<VB : ViewDataBinding, VM : Any, T : Any?> {
    private val itemViewModelCaches by lazy { mutableMapOf<Int, VM?>() }

    fun clear() {
        itemViewModelCaches.clear()
    }

    fun bind(holder: BindingHolder<VB>, position: Int, item: T?) {
        // position 位置缓存的 item viewModel
        val cacheItemViewModel = itemViewModelCaches[position]
        // 创建 item viewModel
        val createItemViewModel = createViewModel(
            holder.binding,
            item,
            cacheItemViewModel
        )
        // 更新
        itemViewModelCaches[position] = createItemViewModel

        setVariable(holder.binding, createItemViewModel)
        holder.binding.executePendingBindings()
    }

    private fun setVariable(binding: VB, viewModel: VM?) {
        try {
            val result = onSetVariable(binding, viewModel)
            if (!result) {
                //set default variable
                binding.setVariable(BR.viewModel, viewModel)
            }
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }


    fun onViewAttachedToWindow(holder: BindingHolder<VB>) {

    }

    /**
     * 在这里设置 ViewDataBinding::setVariable(int variableId, @Nullable Object value);
     */
    open fun onSetVariable(binding: VB, viewModel: VM?): Boolean {
        return false
    }

    /**
     * 创建viewModel
     */
    abstract fun createViewModel(
        binding: VB,
        item: T?,
        cacheItemViewModel: VM?
    ): VM?

    /**
     * 创建viewType类型的ViewDataBinding
     */
    fun createBinding(parent: ViewGroup, viewType: Int): VB{
        val binding = onCreateBinding(parent, viewType)

        val appTheme = ViewTreeAppThemeViewModel.get(parent)
        binding.setVariable(BR.themeViewModel, appTheme)

        val lifecycleOwner = ViewTreeLifecycleOwner.get(parent)
        binding.lifecycleOwner = lifecycleOwner

        return binding
    }

    abstract fun onCreateBinding(parent: ViewGroup, viewType: Int): VB
}