package com.victor.myspot.login.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.victor.myspot.R
import com.victor.myspot.databinding.CreateAccountFragmentBinding
import com.victor.myspot.login.presentation.viewintent.CreateAccountViewIntent
import com.victor.myspot.login.presentation.viewmodel.CreateAccountViewModel
import com.victor.myspot.login.presentation.viewstate.CreateAccountViewState
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountFragment : Fragment(R.layout.create_account_fragment) {
    private val binding by viewBinding(CreateAccountFragmentBinding::bind)
    private val viewModel: CreateAccountViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewState = viewModel.viewState
        }
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            buttonConfirmRegister.setOnClickListener {
                viewModel.dispatchViewIntent(
                    CreateAccountViewIntent.RegisterUserIntent(
                        email = textEmail.text.toString(),
                        password = textPassword.text.toString()
                    )
                )
            }

            buttonGoToLogin.setOnClickListener { showLoginFragment() }
        }
    }

    private fun initObservers() = with(viewModel.viewState) {
        viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                CreateAccountViewState.Action.NavigateToHome -> showHomeFragment()
                is CreateAccountViewState.Action.ShowErrorMessage -> showErrorToast(action.message)
            }
        }
    }

    private fun showHomeFragment() {
        findNavController().navigate(R.id.action_createAccountFragment_to_homeFragment)
    }

    private fun showLoginFragment() {
        findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
    }

    private fun showErrorToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    companion object {
        fun newInstance() = CreateAccountFragment()
    }
}