package com.david0926.sunrinthon2021.view.main.main3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.FragmentMain3Binding
import com.david0926.sunrinthon2021.view.base.MvvmFragment

class Main3Fragment :
    MvvmFragment<FragmentMain3Binding, Main3FragmentViewModel>(R.layout.fragment_main3) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(Main3FragmentViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnClick.setOnClickListener {
            viewModel.addCounter()
        }

        return binding.root
    }

}