package com.joenjogu.nexflix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joenjogu.nexflix.databinding.FragmentLatestBinding

class LatestFragment : Fragment() {
    private lateinit var binding: FragmentLatestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_latest, container, false)
        val adapter = LatestMovieAdapter()
        binding.adapter = adapter

        return binding.root
    }
}
