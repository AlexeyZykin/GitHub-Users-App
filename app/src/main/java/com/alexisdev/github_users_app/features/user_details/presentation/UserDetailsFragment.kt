package com.alexisdev.github_users_app.features.user_details.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alexisdev.github_users_app.R
import com.alexisdev.github_users_app.app.MainActivity
import com.alexisdev.github_users_app.core.DateConverter
import com.alexisdev.github_users_app.databinding.FragmentUserDetailsBinding
import com.alexisdev.github_users_app.features.user_list.presentation.UserListUiState
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding
    private val args by navArgs<UserDetailsFragmentArgs>()
    private val viewModel by viewModel<UserDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUserDetails(args.username)
        (activity as MainActivity).supportActionBar?.title = args.username
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.userDetails.observe(viewLifecycleOwner) {
            when (it) {
                is UserDetailsUiState.Success -> {
                    if (it.data != null) showUserDetails(it.data)
                }

                is UserDetailsUiState.Error ->
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()

                is UserDetailsUiState.Loading -> {}
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showUserDetails(userDetailsUi: UserDetailsUi) {
        Glide
            .with(requireContext())
            .load(userDetailsUi.avatarUrl)
            .circleCrop()
            .into(binding.imgUserAvatar)

        binding.apply {
            if (userDetailsUi.name != null) tvName.text = userDetailsUi.name
            tvFollowers.text = "${userDetailsUi.followers} ${getString(R.string.followers)}"
            tvFollowing.text = "${userDetailsUi.following} ${getString(R.string.following)}"
            if (userDetailsUi.email != null) tvEmail.text = userDetailsUi.email
            if (userDetailsUi.company != null) tvCompany.text = userDetailsUi.company
            tvCreatedAt.text = DateConverter.convertDateToString(userDetailsUi.createdAt)
        }
    }
}