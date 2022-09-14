package com.victor.myspot.login.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.victor.myspot.R
import com.victor.myspot.databinding.LoginFragmentBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val binding by viewBinding(LoginFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}