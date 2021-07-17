package com.david0926.sunrinthon2021.view.main.main1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.FragmentMain1Binding
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.article.ArticleActivity
import com.david0926.sunrinthon2021.view.base.MvvmFragment

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

        binding.recyclerMain1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val adapter = Main1RecyclerAdapter(UserCache.getUser(requireContext()))
        adapter.onItemClick = {
            val articleIntent = Intent(requireContext(), ArticleActivity::class.java)
            articleIntent.putExtra("post", viewModel.PostList[it-1])
            articleIntent.putExtra("user", UserCache.getUser(requireContext()))
            startActivity(articleIntent)
        }



        binding.recyclerMain1.adapter = adapter

        binding.swipeMain1.setOnRefreshListener { viewModel.nextPage ({  },requireContext()) }
        viewModel.isLoaded.observe(viewLifecycleOwner) { isLoaded ->
            binding.swipeMain1.isRefreshing = !isLoaded
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled && !binding.recyclerMain1.canScrollVertically(-1)) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    } else binding.recyclerMain1.smoothScrollToPosition(0)
                }
            })

        viewModel.nextPage ({  },requireContext())

        return binding.root
    }
}