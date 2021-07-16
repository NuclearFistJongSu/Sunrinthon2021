package com.david0926.sunrinthon2021.view.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.ActivityMainBinding
import com.david0926.sunrinthon2021.view.base.MvvmActivity

class MainActivity : MvvmActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel


    }
}