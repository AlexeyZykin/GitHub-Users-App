package com.alexisdev.github_users_app.features.user_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexisdev.github_users_app.core.Mapper
import com.alexisdev.github_users_app.databinding.UserItemBinding
import com.bumptech.glide.Glide

class UserListAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(), Mapper<List<UserUi>> {

    private val list = mutableListOf<UserUi>()

    class UserListViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
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

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun map(source: List<UserUi>) {
        val diff = DiffUtil(list, source)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }

    interface ClickListener {
        fun onClickUser(userUi: UserUi)
    }

    class DiffUtil(
        private val oldList: List<UserUi>,
        private val newList: List<UserUi>
    ) : androidx.recyclerview.widget.DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}