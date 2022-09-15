package com.victor.myspot.login.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.victor.myspot.R
import com.victor.myspot.databinding.LoginFragmentBinding
import com.victor.myspot.login.presentation.viewintent.LoginViewIntent
import com.victor.myspot.login.presentation.viewmodel.LoginViewModel
import com.victor.myspot.login.presentation.viewstate.LoginViewState
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val binding by viewBinding(LoginFragmentBinding::bind)
    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewState = viewModel.viewState
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            buttonConfirmLogin.setOnClickListener {
                viewModel.dispatchViewIntent(
                    LoginViewIntent.SignInIntent(
                        email = textEmail.text.toString(),
                        password = textPassword.text.toString()
                    )
                )
            }

            buttonGoToRegister.setOnClickListener { showCreateAccountFragment() }
        }
    }

    private fun initObservers() = with(viewModel.viewState) {
        viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                LoginViewState.Action.NavigateToHomeFragment -> showHomeFragment()
                is LoginViewState.Action.ShowErrorToast -> showErrorToast(action.message)
            }
        }
    }

    private fun showHomeFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun showCreateAccountFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
    }

    private fun showErrorToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    companion object {
        fun newInstance() = CreateAccountFragment()
    }
}