package com.victor.myspot.movies.presentation.view.newmovie.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.victor.myspot.R
import com.victor.myspot.core.presentation.RecyclerItemClickListener
import com.victor.myspot.databinding.NewMovieFragmentBinding
import com.victor.myspot.movies.presentation.view.FormatInput
import com.victor.myspot.movies.presentation.view.newmovie.viewintent.NewMovieViewIntent
import com.victor.myspot.movies.presentation.view.newmovie.viewmodel.NewMovieViewModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DEFAULT_CATEGORY = "Favoritos"

class NewMovieFragment : Fragment(R.layout.new_movie_fragment), FormatInput {
    private val binding by viewBinding(NewMovieFragmentBinding::bind)
    private val viewModel: NewMovieViewModel by viewModel()
    private var category = DEFAULT_CATEGORY

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
        }

        initListeners()
        initObservers()

        viewModel.dispatchViewIntent(NewMovieViewIntent.GetCategories)
    }

    private fun initObservers() = with(viewModel.viewState) {
        movieUiModel.observe(viewLifecycleOwner) { uiModel ->
            setupRecyclerView(binding.rvNewMovies, uiModel.items)
        }

        categories.observe(viewLifecycleOwner) { categories ->
            setupSpinner(categories)
        }
    }

    private fun setupSpinner(categories: List<String>) {
        val adapter =
            ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                categories,
            )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerCategory.adapter = adapter
    }

    private fun setupRecyclerView(rv: RecyclerView, movies: List<ItemUiModel>) {
        rv.apply {
            adapter = MoviesAdapter(movies)
            hasFixedSize()
        }
    }

    private fun initListeners() = with(binding) {
        buttonMovie.setOnClickListener {
            viewModel.dispatchViewIntent(
                NewMovieViewIntent.GetMovieIntent(
                    movie = formattedInput(editTextMovie.text.toString())
                )
            )
        }

        spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    category = spinnerCategory.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // Do nothing
                }
            }

        rvNewMovies.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireContext(),
                binding.rvNewMovies,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
//                    TODO("Not yet implemented")
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        viewModel.viewState.movieUiModel.value?.items?.let { safeMovie ->
                            viewModel.dispatchViewIntent(
                                NewMovieViewIntent.SaveMovie(
                                    favoriteMovie = safeMovie[position],
                                    category = category
                                )
                            )
                        }
                    }
                }
            )
        )
    }
}