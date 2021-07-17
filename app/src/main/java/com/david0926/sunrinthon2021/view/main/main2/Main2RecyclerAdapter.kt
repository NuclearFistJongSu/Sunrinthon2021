package com.david0926.sunrinthon2021.view.main.main2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.databinding.RowPostBinding

class Main2RecyclerAdapter(userModel: UserModel) :
    RecyclerView.Adapter<Main2RecyclerAdapter.Main2RecyclerHolder>() {

    class Main2RecyclerHolder(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root)

    private var posts = ArrayList<Post>()
    private var user = userModel

    var onItemClick: (position: Int) -> Unit = {}

    fun setPosts(posts: ArrayList<Post>) {
        val diffCallBack = Main2RecyclerDiffCallBack(this.posts, posts)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)

        this.posts.clear()
        this.posts.addAll(posts)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Main2RecyclerHolder {
        return Main2RecyclerHolder(
            RowPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: Main2RecyclerHolder, position: Int) {
        holder.binding.position = 1
        holder.binding.user = user

        holder.binding.root.setOnClickListener { onItemClick.invoke(position) }
        holder.binding.post = posts[position]
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class Main2RecyclerDiffCallBack(
        private var oldList: List<Post>,
        private var newList: List<Post>
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