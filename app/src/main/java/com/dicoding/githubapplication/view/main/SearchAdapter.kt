package com.dicoding.githubapplication.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.githubapplication.databinding.ItemRowUserBinding
import com.dicoding.githubapplication.model.response.ResponseSearch
import com.bumptech.glide.Glide

class SearchAdapter(private val listUser: ArrayList<ResponseSearch>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[position])
        }
    }

    override fun getItemCount(): Int = listUser.size

    inner class ViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ResponseSearch) {
            with(binding) {
                imgUserAvatar.loadImage(user.avatarUrl)
                tvName.text = user.login
                tvUrl.text = user.htmlUrl
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResponseSearch)
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .circleCrop()
            .into(this)
    }
}
