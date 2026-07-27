package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import edu.vt.cs5254.dreamcatcher.databinding.FragmentDreamListBinding


/**
 * CS 5254 Project 2A - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.26
 */

class DreamListFragment : Fragment() {

    private var _binding: FragmentDreamListBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "FragmentDreamListBinding is null!" }

    private val dlvm: DreamListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // returning a view --> similar to onCreate (never return null here, but they do in the book)
        _binding = FragmentDreamListBinding.inflate(inflater, container, false)

        // recycler view layout can be defined here, but it is below in onViewCreated for this project

        val dreams = dlvm.dreams
        val adapter = DreamListAdapter(dreams)
        binding.dreamRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dreamRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.dreamRecyclerView.adapter = DreamListAdapter(dlvm.dreams)

        // normally we would updateView here, but not needed
    }

    override fun onDestroyView() { // bc fragment can outlive the view
        super.onDestroyView()
        _binding =
            null // only used when inflating the view and destroying view, setting it back to null
    }

    private fun updateView() {
        // In DreamListFragment, nothing is done in updateView().
    }
}

