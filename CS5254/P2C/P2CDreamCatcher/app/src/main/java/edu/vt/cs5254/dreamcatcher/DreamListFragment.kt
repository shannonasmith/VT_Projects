package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.vt.cs5254.dreamcatcher.databinding.FragmentDreamListBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

class DreamListFragment : Fragment() {

    private var _binding: FragmentDreamListBinding? = null

    private val binding
        get() = checkNotNull(_binding) { "FragmentDreamListBinding is null!" }

    @DelicateCoroutinesApi
    private val dlvm: DreamListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDreamListBinding.inflate(inflater, container, false)

       binding.dreamRecyclerView.layoutManager = LinearLayoutManager(context) // 12.7


        // 3.21 Q&A - add new dream via menu bar
        requireActivity().addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.fragment_dream_list, menu)
                }

                // 15.8
                @DelicateCoroutinesApi
                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.new_dream -> {
                            // process this...
                            showNewDream() // uses private helper below onDestroyView
                            true
                        }
                        else -> false
                    }
                }
            },
            viewLifecycleOwner
        )

        return binding.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dreamRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.apply {
            noDreamAddButton.setOnClickListener {
                showNewDream()
            }
        }

        getItemTouchHelper().attachToRecyclerView(binding.dreamRecyclerView)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dlvm.loadDreamsFlow.collect { dreams -> // 12.27
                    /* Before constructing the adapter, but within the collect{} block for dreams,
                        set the visibility of the new views to either View.VISIBLE or View.GONE,
                        based on whether the collected dreams list is empty or not: */
                    binding.noDreamText.visibility =
                        if (dreams.isEmpty()) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                    binding.noDreamAddButton.visibility =
                        if (dreams.isEmpty()) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                    // set the adapter
                    binding.dreamRecyclerView.adapter = DreamListAdapter(dreams) { dreamId ->
                        // findNavController().navigate(R.id.show_dream_detail) // 13.8
                        // 13.11 + 13.13
                        findNavController().navigate(
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

    // 15.8 / 3.21 Q&A --> private helper - add new dream via menu bar
    @DelicateCoroutinesApi
    private fun showNewDream() {
        // addDream is a suspend function, so much put entire process in a coroutine scope
        viewLifecycleOwner.lifecycleScope.launch {
            val newDream = Dream()
            // add to database... start at DreamDao
            dlvm.addDream(newDream)
            // then navigate... see dreamRecyclerView in viewLifecycleOwner above...
            findNavController().navigate(
                DreamListFragmentDirections.showDreamDetail(
                    newDream.id
                )
            )
        }
    }

    // 15.8 / 3.21 Q&A --> swipe to delete
    @DelicateCoroutinesApi
    private fun getItemTouchHelper(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dreamHolder = viewHolder as DreamHolder // cast viewHolder as DreamHolder object
                val dreamToDelete = dreamHolder.boundDream // in dreamListAdapter
                dlvm.deleteDream(dreamToDelete)
            }
        })
    }
}

