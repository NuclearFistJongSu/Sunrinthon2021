package com.david0926.sunrinthon2021.view.main.main2

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.network.post.PostManager
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.main.main1.Main1RecyclerAdapter


class Main2FragmentViewModel : ViewModel() {

    val isLoaded = MutableLiveData(false)

    companion object {
        @JvmStatic
        @BindingAdapter("bindPosts2")
        fun bindPosts2(r: RecyclerView, posts: ObservableArrayList<Post>?) {
            val adapter: Main2RecyclerAdapter? = r.adapter as Main2RecyclerAdapter?
            if (adapter == null || posts == null) return
            adapter.setPosts(posts)
        }
    }

    val PostList = ObservableArrayList<Post>()

    fun getPersonalPostFromRepo(context: Context) {
        isLoaded.value = false
        val postManager = PostManager(context)
        postManager.getPosts(0, Int.MAX_VALUE, { response, datas ->
            isLoaded.value = true
            if (response.success) {
                if (datas != null) {
                    for (data in datas) {
                        if (data.by!!._id == UserCache.getUser(context)._id)
                            PostList.add(data)
                    }
                    PostList.reverse()
                }
            }
        }, {
            isLoaded.value = true
            it.printStackTrace()
        })
    }
}