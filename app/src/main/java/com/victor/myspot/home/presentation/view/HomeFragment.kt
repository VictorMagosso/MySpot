package com.victor.myspot.home.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.victor.myspot.R
import com.victor.myspot.databinding.HomeFragmentBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding by viewBinding(HomeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}