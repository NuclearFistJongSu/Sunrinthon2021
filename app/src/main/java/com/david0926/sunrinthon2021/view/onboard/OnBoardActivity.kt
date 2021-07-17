package com.david0926.sunrinthon2021.view.onboard

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.ActivityOnBoardBinding
import com.david0926.sunrinthon2021.util.SharedPreferenceUtil
import com.david0926.sunrinthon2021.view.base.MvvmActivity
import com.david0926.sunrinthon2021.view.main.MainActivity

class OnBoardActivity :
    MvvmActivity<ActivityOnBoardBinding, OnBoardViewModel>(R.layout.activity_on_board) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OnBoardViewModel::class.java)
        binding.viewModel = viewModel

        //I know this is bad
        viewModel.fragments.addAll(
            listOf(
                OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_1),
                OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_2),
                OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_3)
            )
        )

        val adapter = OnBoardPagerAdapter(this, viewModel.fragments)
        binding.pagerOnBoard.adapter = adapter

        binding.btnOnBoardFinish.setOnClickListener { finishOnBoard() }
        binding.btnOnBoardSkip.setOnClickListener { finishOnBoard() }
    }

    private fun finishOnBoard() {
        SharedPreferenceUtil.put(this@OnBoardActivity, "state", "login")
        startActivity(Intent(this@OnBoardActivity, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (!viewModel.isFirstPage) viewModel.previousPage() else super.onBackPressed()
    }
}