package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.databinding.FragmentMoreDataBinding

class MoreDataFragment : Fragment() {

    private lateinit var binding: FragmentMoreDataBinding
    private lateinit var adapter: GraphAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMoreDataBinding.inflate(layoutInflater)
        adapter = GraphAdapter()
        binding.recyclerviewItems.adapter = adapter
        adapter.set(mutableListOf(Graph(mutableListOf("s"), mutableListOf(1.0f)),
            Graph(mutableListOf("s"), mutableListOf(1.0f))))
        return binding.root
    }

}