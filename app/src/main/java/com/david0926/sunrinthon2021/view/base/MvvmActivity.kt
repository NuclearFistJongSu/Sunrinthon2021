package com.david0926.sunrinthon2021.view.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

open class MvvmActivity<D : ViewDataBinding, V : ViewModel>(@LayoutRes private val layoutResId: Int) :
    BaseActivity<D>(layoutResId) {
    protected lateinit var viewModel: V
}