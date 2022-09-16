package com.victor.myspot.movies.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.victor.myspot.R
import com.victor.myspot.databinding.MoviesFragmentBinding
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.presentation.view.compose.CategoryListView
import com.victor.myspot.movies.presentation.viewintent.MoviesViewIntent
import com.victor.myspot.movies.presentation.viewmodel.MoviesViewModel
import com.victor.myspot.movies.presentation.viewstate.MoviesViewState
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
        initActionObserver()
        viewModel.dispatchViewIntent(MoviesViewIntent.GetFavoritesMovies)
    }

    private fun initActionObserver() = with(viewModel.viewState) {
        action.observe(viewLifecycleOwner) { action ->
            when(action) {
                is MoviesViewState.Action.ErrorDeletingMovie -> showErrorMessage(action.message)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupCategoriesComposeList(movies: List<MoviesPerCategoryModel>) {
        binding.composeCategoriesList.setContent {
            CategoryListView(movies, findNavController(), viewModel)
        }
    }


    private fun initObservers() = with(viewModel.viewState) {
        moviesPerCategoryModel.observe(viewLifecycleOwner) { movies ->
            setupCategoriesComposeList(movies)
        }
    }

    private fun initListeners() = with(binding) {
        icGoBack.setOnClickListener { findNavController().popBackStack() }
    }
}
