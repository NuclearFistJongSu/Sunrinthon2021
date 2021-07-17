package com.david0926.sunrinthon2021.view.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.data.post.Comment
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.databinding.ActivityArticleBinding
import com.david0926.sunrinthon2021.network.post.PostManager
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.base.MvvmActivity
import com.david0926.sunrinthon2021.view.dialog.LoadingDialog

class ArticleActivity : MvvmActivity<ActivityArticleBinding, ArticleViewModel>(R.layout.activity_article) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerArticle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = ArticleRecyclerAdapter(UserCache.getUser(this))
        binding.recyclerArticle.adapter = adapter

        val post = intent.getSerializableExtra("post") as Post
        binding.post = post
        viewModel.CommentList.addAll(post.comments as Collection<Comment>)

        binding.toolbarArticle.setNavigationOnClickListener {
            finish()
        }

        binding.btnArticleComment.setOnClickListener {
            val message = viewModel.comment.value!!
            viewModel.comment.value=""
            val postManager = PostManager(this)
            postManager.addComment(UserCache.getUser(this)._id, message, {response, posts ->
                if(response.success) {
                    adapter.setComments(post.comments as ArrayList<Comment>)
                } else {
                    return@addComment
                }
            }, {
                it.printStackTrace()
            })
        }

    }
}