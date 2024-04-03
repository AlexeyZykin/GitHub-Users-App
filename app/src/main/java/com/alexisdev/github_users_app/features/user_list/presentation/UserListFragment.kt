package com.alexisdev.github_users_app.features.user_list.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchUsers()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun initRecyclerView() {
        adapter = UserListAdapter(
            object : UserListAdapter.ClickListener {
                override fun onClickUser(userUi: UserUi) {
                    val action = UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(userUi.login)
                    findNavController().navigate(action)
                }
            }
        )
        binding.rvUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUserList.adapter = adapter.withLoadStateHeaderAndFooter(
            UserListLoadStateAdapter(),
            UserListLoadStateAdapter()
        )
    }

    private fun subscribeObserver() {
        viewModel.users.observe(viewLifecycleOwner) {
           adapter.submitData(lifecycle, it)
        }
    }
}