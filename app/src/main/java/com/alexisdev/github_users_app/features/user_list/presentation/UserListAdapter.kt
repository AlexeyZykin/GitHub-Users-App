package com.alexisdev.github_users_app.features.user_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexisdev.github_users_app.databinding.UserItemBinding
import com.bumptech.glide.Glide

class UserListAdapter(private val clickListener: ClickListener) :
    PagingDataAdapter<UserUi, UserListAdapter.UserListViewHolder>(UserComparator) {

    class UserListViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userUi: UserUi, clickListener: ClickListener) {
            binding.tvUserLoginListItem.text = userUi.login
            binding.tvUserIdListItem.text = "Id: ${userUi.id}"
            binding.cardUser.setOnClickListener { clickListener.onClickUser(userUi) }
            Glide
                .with(binding.imgUserAvatarListItem.context)
                .load(userUi.avatarUrl)
                .centerCrop()
                .into(binding.imgUserAvatarListItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) holder.bind(user, clickListener)
    }

    interface ClickListener {
        fun onClickUser(userUi: UserUi)
    }

    object UserComparator : DiffUtil.ItemCallback<UserUi>() {
        override fun areItemsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
            return oldItem == newItem
        }
    }
}