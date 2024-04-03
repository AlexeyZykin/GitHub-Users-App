package com.alexisdev.github_users_app.features.user_list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexisdev.github_users_app.databinding.ErrorItemBinding
import com.alexisdev.github_users_app.databinding.ProgressItemBinding

class UserListLoadStateAdapter() : LoadStateAdapter<UserListLoadStateAdapter.ItemViewHolder>() {

    abstract class ItemViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        abstract fun bind(loadState: LoadState)
    }

    class ProgressViewHolder(private val binding: ProgressItemBinding) :
        ItemViewHolder(binding.root) {
        override fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE

        }
    }

    class ErrorViewHolder(private val binding: ErrorItemBinding) : ItemViewHolder(binding.root) {
        override fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error)
                binding.tvErrorMsg.visibility = View.VISIBLE
            else
                binding.tvErrorMsg.visibility = View.GONE
        }

    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when (loadState) {
            is LoadState.NotLoading -> error("not loading")

            is LoadState.Loading -> ProgressViewHolder(
                ProgressItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            is LoadState.Error -> ErrorViewHolder(
                ErrorItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("not loading"
        )
        is LoadState.Loading -> PROGRESS

        is LoadState.Error -> ERROR
    }

    private companion object {
        private const val PROGRESS = 0
        private const val ERROR = 1
    }

}

