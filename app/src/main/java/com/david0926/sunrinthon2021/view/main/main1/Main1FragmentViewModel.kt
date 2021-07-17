package com.david0926.sunrinthon2021.view.main.main1

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.network.post.PostManager


class Main1FragmentViewModel : ViewModel() {

    var hasNext = true; // 잘 됐을지 안됐을지 전혀 모르겠습니다!!
    var page = 0

    val PostList = ObservableArrayList<Post>()
    val AmountPerPage = 5; //  한 페이지당 보일 갯수

    val isLoaded = MutableLiveData(false)

    companion object {
        @JvmStatic
        @BindingAdapter("bindPosts")
        fun bindPosts(r: RecyclerView, posts: ObservableArrayList<Post>?) {
            val adapter: Main1RecyclerAdapter? = r.adapter as Main1RecyclerAdapter?
            if (adapter == null || posts == null) return
            adapter.setPosts(posts)
        }
    }

    private fun getPostFromRepo(page: Int) {
        isLoaded.value = false
        val postManager = PostManager()
        postManager.getPosts(AmountPerPage, page, {response, posts ->
            isLoaded.value = true
            if(response.success) {
                if(page == 0)
                    PostList.clear()

                if(posts!!.size != page)
                    hasNext = false

                PostList.addAll(posts!!)
            } else {
                hasNext = false
                return@getPosts
            }
         }, {
            isLoaded.value = true
            it.printStackTrace()
        })
    }

    fun nextPage(finish: () -> Unit) {
        if(hasNext) {
            getPostFromRepo(++page)
            if(hasNext) {
                finish()
            }
        }
    }
}