package com.david0926.sunrinthon2021.view.main.main4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.FragmentMain1Binding
import com.david0926.sunrinthon2021.databinding.FragmentMain4Binding
import com.david0926.sunrinthon2021.view.base.MvvmFragment
import com.david0926.sunrinthon2021.view.main.main3.Main3FragmentViewModel

class Main4Fragment :
    MvvmFragment<FragmentMain4Binding, Main4FragmentViewModel>(R.layout.fragment_main4) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(Main4FragmentViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnClick.setOnClickListener {
            viewModel.addCounter()
        }

        return binding.root
    }

}