package com.victor.myspot.movies.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.victor.myspot.R
import com.victor.myspot.databinding.MoviesFragmentBinding
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.presentation.view.compose.CategoryListView
import com.victor.myspot.movies.presentation.viewmodel.MoviesViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.movies_fragment), FormatInput {
    private val binding by viewBinding(MoviesFragmentBinding::bind)
    private val viewModel: MoviesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
        }

        initListeners()
        initObservers()
    }

    private fun setupCategoriesComposeList(movies: List<MoviesPerCategoryModel>) {
        binding.composeCategoriesList.setContent {
            CategoryListView(movies)
        }
    }


    private fun initObservers() = with(viewModel.viewState) {
        moviesPerCategoryModel.observe(viewLifecycleOwner) { movies ->
            setupCategoriesComposeList(movies)
        }
    }

    private fun initListeners() = with(binding) {
        buttonAddCategory.setOnClickListener { findNavController().navigate(R.id.action_moviesFragment_to_newMovieFragment) }
    }
}
