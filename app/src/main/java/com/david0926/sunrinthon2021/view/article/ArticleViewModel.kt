package com.david0926.sunrinthon2021.view.article

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.sunrinthon2021.data.post.Comment
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.network.post.PostManager
import com.david0926.sunrinthon2021.view.main.main1.Main1RecyclerAdapter

class ArticleViewModel :ViewModel(){

    val comment = MutableLiveData("")

    val CommentList = ObservableArrayList<Comment>()

    companion object {
        @JvmStatic
        @BindingAdapter("bindComment")
        fun bindComment(r: RecyclerView, comments: ObservableArrayList<Comment>?) {
            val adapter: ArticleRecyclerAdapter? = r.adapter as ArticleRecyclerAdapter?
            if (adapter == null || comments == null) return
            adapter.setComments(comments)
        }
    }
}