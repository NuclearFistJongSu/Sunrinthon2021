package com.david0926.sunrinthon2021.view.main.main1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.FragmentMain1Binding
import com.david0926.sunrinthon2021.view.base.MvvmFragment
import com.david0926.sunrinthon2021.view.main.MainViewModel

class Main1Fragment :
    MvvmFragment<FragmentMain1Binding, Main1FragmentViewModel>(R.layout.fragment_main1) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(Main1FragmentViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnClick.setOnClickListener {
            viewModel.addCounter()
        }

        return binding.root
    }

}