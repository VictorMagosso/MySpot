package com.victor.myspot.movies.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.victor.myspot.R
import com.victor.myspot.databinding.MoviesFragmentBinding
import com.victor.myspot.movies.presentation.viewintent.MoviesViewIntent
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

    private fun initObservers() = with(viewModel.viewState) {
        movieUiModel.observe(viewLifecycleOwner) { uiModel ->
            with(binding.titleMovie) {
                text = uiModel.items.toString()
            }
        }
    }

    private fun initListeners() = with(binding) {
        buttonMovie.setOnClickListener {
            viewModel.dispatchViewIntent(
                MoviesViewIntent.GetMovieIntent(
                    movie = formattedInput(editTextMovie.text.toString())
                )
            )
        }
    }
}
