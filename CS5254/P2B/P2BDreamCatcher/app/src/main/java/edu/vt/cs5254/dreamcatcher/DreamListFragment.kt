package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.vt.cs5254.dreamcatcher.databinding.FragmentDreamListBinding
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
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
    ): View {
        _binding = FragmentDreamListBinding.inflate(inflater, container, false)
        binding.dreamRecyclerView.layoutManager = LinearLayoutManager(context) // 12.7
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dreamRecyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dlvm.loadDreamsFlow.collect { dreams -> // 12.27
                    binding.dreamRecyclerView.adapter = DreamListAdapter(dreams) { dreamId ->
                        // findNavController().navigate(R.id.show_dream_detail) // 13.8
                        findNavController().navigate( // 13.11 + 13.13
                            DreamListFragmentDirections.showDreamDetail(
                                dreamId
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

