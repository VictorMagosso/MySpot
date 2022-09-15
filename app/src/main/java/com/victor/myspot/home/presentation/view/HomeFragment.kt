package com.victor.myspot.home.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.victor.myspot.R
import com.victor.myspot.core.presentation.RecyclerItemClickListener
import com.victor.myspot.databinding.HomeFragmentBinding
import com.victor.myspot.home.presentation.adapter.CategoriesAdapter
import com.victor.myspot.home.presentation.viewintent.HomeViewIntent
import com.victor.myspot.home.presentation.viewmodel.HomeViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding by viewBinding(HomeFragmentBinding::bind)
    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            setupRecyclerView(rvCategories)
        }

        initListeners()
    }

    private fun setupRecyclerView(rv: RecyclerView) {
        rv.apply {
            adapter = CategoriesAdapter()
            hasFixedSize()
        }
    }

    private fun initListeners() = with(binding) {
        buttonLogOut.setOnClickListener {
            viewModel.dispatchViewIntent(HomeViewIntent.Logout)
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        rvCategories.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireActivity(),
                rvCategories,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        handleFragmentNavigation(position)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // TODO("Not yet implemented")
                    }
                })
        )
    }

    private fun handleFragmentNavigation(position: Int) {
        when(position) {
            0 -> findNavController().navigate(R.id.action_homeFragment_to_songsFragment)
            1 -> findNavController().navigate(R.id.action_homeFragment_to_moviesFragment)
        }
    }
}
