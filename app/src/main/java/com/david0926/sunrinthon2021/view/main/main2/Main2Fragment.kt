package com.david0926.sunrinthon2021.view.main.main2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.FragmentMain2Binding
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.article.ArticleActivity
import com.david0926.sunrinthon2021.view.base.MvvmFragment
import com.david0926.sunrinthon2021.view.main.main1.Main1RecyclerAdapter

class Main2Fragment :
    MvvmFragment<FragmentMain2Binding, Main2FragmentViewModel>(R.layout.fragment_main2) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(Main2FragmentViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerMain2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val adapter = Main2RecyclerAdapter(UserCache.getUser(requireContext()))
        adapter.onItemClick = {
            val articleIntent = Intent(requireContext(), ArticleActivity::class.java)
            articleIntent.putExtra("post", viewModel.PostList[it-1])
            articleIntent.putExtra("user", UserCache.getUser(requireContext()))
            startActivity(articleIntent)
        }

        binding.recyclerMain2.adapter = adapter

        binding.swipeMain2.setOnRefreshListener { viewModel.getPersonalPostFromRepo(requireContext()) }

        viewModel.getPersonalPostFromRepo(this.requireContext())
        return binding.root
    }

}