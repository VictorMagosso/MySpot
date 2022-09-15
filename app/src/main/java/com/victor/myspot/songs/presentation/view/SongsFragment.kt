package com.victor.myspot.songs.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.victor.myspot.R
import com.victor.myspot.databinding.SongsFragmentBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class SongsFragment : Fragment(R.layout.songs_fragment) {
    private val binding by viewBinding(SongsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}