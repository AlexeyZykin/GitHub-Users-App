package com.alexisdev.github_users_app.features.user_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.alexisdev.github_users_app.R
import com.alexisdev.github_users_app.databinding.FragmentUserListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var adapter: UserListAdapter
    private val viewModel by viewModel<UserListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObserver()
    }

    private fun initRecyclerView() {
        adapter = UserListAdapter(
            object : UserListAdapter.ClickListener {
                override fun onClickUser(userUi: UserUi) {
                    //Navigation
                }
            }
        )
        binding.rvUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUserList.adapter = adapter
    }

    private fun subscribeObserver() {
        viewModel.users.observe(viewLifecycleOwner) {
            when (it) {
                is UserListUiState.Success -> {
                    it.data?.let { it1 -> adapter.map(it1) }
                }

                is UserListUiState.Error ->
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()

                is UserListUiState.Loading -> {}
            }
        }
    }

}