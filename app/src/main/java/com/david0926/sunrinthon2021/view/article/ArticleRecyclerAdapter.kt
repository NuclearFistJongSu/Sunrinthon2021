package com.david0926.sunrinthon2021.view.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.post.Comment
import com.david0926.sunrinthon2021.databinding.RowCommentBinding

class ArticleRecyclerAdapter(userModel: UserModel) :
    RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleRecyclerHolder>() {

    class ArticleRecyclerHolder(var binding: RowCommentBinding) : RecyclerView.ViewHolder(binding.root)

    private var comments = ArrayList<Comment>()
    private var user = userModel

    var onItemClick: (position: Int) -> Unit = {}

    fun setComments(comments: ArrayList<Comment>) {
        val diffCallBack = ArticleRecyclerDiffCallBack(this.comments, comments)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)

        this.comments.clear()
        this.comments.addAll(comments)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleRecyclerHolder {
        return ArticleRecyclerHolder(
            RowCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleRecyclerHolder, position: Int) {
        holder.binding.user = user

        holder.binding.root.setOnClickListener { onItemClick.invoke(position) }
        holder.binding.comment = comments[position]
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    class ArticleRecyclerDiffCallBack(
        private var oldList: List<Comment>,
        private var newList: List<Comment>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldListSize != newListSize || newList.isEmpty()) return false
            return oldList[oldItemPosition]._id == newList[newItemPosition]._id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldListSize != newListSize || newList.isEmpty()) return false
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }

}