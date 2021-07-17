package com.david0926.sunrinthon2021.view.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.ActivityMainBinding
import com.david0926.sunrinthon2021.view.base.MvvmActivity
import com.david0926.sunrinthon2021.view.main.main1.Main1Fragment
import com.david0926.sunrinthon2021.view.main.main2.Main2Fragment
import com.david0926.sunrinthon2021.view.main.main3.Main3Fragment
import com.david0926.sunrinthon2021.view.main.main4.Main4Fragment

class MainActivity : MvvmActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.fragments.addAll(
            listOf(
                Main1Fragment(),
                Main2Fragment(),
                Main3Fragment(),
                Main4Fragment()
            )
        )

        binding.bottomMain.setOnItemSelectedListener {
            viewModel.page.value = when (it.itemId) {
                R.id.action_1 -> 0
                R.id.action_2 -> 1
                R.id.action_3 -> 2
                else -> 3
            }
            return@setOnItemSelectedListener true
        }
    }
}