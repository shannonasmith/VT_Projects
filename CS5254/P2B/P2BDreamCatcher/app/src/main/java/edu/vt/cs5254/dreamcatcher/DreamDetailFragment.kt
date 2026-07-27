package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.vt.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

@DelicateCoroutinesApi
class DreamDetailFragment : Fragment() {

    private var _binding: FragmentDreamDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentDreamDetailBinding is null!"
        }

    private val args: DreamDetailFragmentArgs by navArgs() // 13.14

    private val ddvm: DreamDetailViewModel by viewModels { // 13.18
        DreamDetailViewModelFactory(args.dreamId) // 13.18
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentDreamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deferredCheckbox.setOnClickListener {
            ddvm.updateDream { oldDream -> // 13.21
                oldDream.copy().apply {
                    entries = if (oldDream.isDeferred) {
                        oldDream.entries.filter {
                            it.kind == DreamEntryKind.CONCEIVED || it.kind == DreamEntryKind.REFLECTION
                        }
                    } else {
                        oldDream.entries + DreamEntry(
                            kind = DreamEntryKind.DEFERRED,
                            dreamId = oldDream.id
                        )
                    }
                }
            }
        }

        binding.fulfilledCheckbox.setOnClickListener {
            ddvm.updateDream { oldDream -> // 13.21
                oldDream.copy().apply {
                    entries = if (oldDream.isFulfilled) {
                        oldDream.entries.dropLast(1)
                    } else {
                        oldDream.entries + DreamEntry(
                            kind = DreamEntryKind.FULFILLED,
                            dreamId = oldDream.id
                        )
                    }
                }
            }
        }

        binding.apply {
            titleText.doOnTextChanged { text, _, _, _ ->
                ddvm.updateDream { oldDream -> // 13.21
                    oldDream.copy(title = text.toString()).apply {
                        entries = oldDream.entries
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch { // 13.19
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ddvm.dream.collect { dream -> // 13.19
                    dream?.let { updateUi(it) }
                }
            }
        }

        // 1 - In onViewCreated(), call fab.setOnClickListener() to navigate to the dialog.
        binding.addReflectionButton.setOnClickListener {
            findNavController().navigate(
                DreamDetailFragmentDirections.addReflection()
            )
        }

        setFragmentResultListener( // 14.8
            ReflectionDialogFragment.REQUEST_KEY
        ) { _, bundle ->
            val entryText = // <-- 14.10 AP
                bundle.getString(ReflectionDialogFragment.BUNDLE_KEY) ?: ""
            ddvm.updateDream { oldDream ->
                // convert oldDream to a new Dream via copy()
                oldDream.copy().apply {
                    entries = oldDream.entries + DreamEntry(
                        text = entryText,
                        kind = DreamEntryKind.REFLECTION,
                        dreamId = oldDream.id
                    )
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun updateUi(dream: Dream) {
        // title text
        /*
        you need to check whether the existing value and the new value being passed in are
        different. If they are different, then you update the EditText. If they are the same,
        you do nothing. This will prevent an infinite loop when you start listening to changes
        on the EditText.
         */
        if (binding.titleText.text.toString() != dream.title) {
            binding.titleText.setText(dream.title)
        }

        // button text/color/visibility
        val entryButtonList = listOf(
            binding.entry0Button,
            binding.entry1Button,
            binding.entry2Button,
            binding.entry3Button,
            binding.entry4Button
        )

        entryButtonList.forEach { it.visibility = View.GONE }

        entryButtonList.zip(dream.entries).forEach { (button, entry) ->
            button.configureForEntry(entry)
        }

        // last updated date
        val dateFormat = DateFormat.format("yyyy-MM-dd 'at' hh:mm:ss a", dream.lastUpdated)
        val dateString = getString(R.string.last_updated_text, dateFormat)
        binding.lastUpdatedText.text = dateString

        // enabled/checked state of both checkboxes --> deferred should start as checked
        binding.deferredCheckbox.isChecked = dream.isDeferred
        binding.deferredCheckbox.isEnabled = !dream.isFulfilled
        binding.fulfilledCheckbox.isChecked = dream.isFulfilled
        binding.fulfilledCheckbox.isEnabled = !dream.isDeferred

        // 14.4
        if (dream.isFulfilled) { // hide FAB if dream is fulfilled
            binding.addReflectionButton.hide()
        } else { // show FAB if dream is not fulfilled
            binding.addReflectionButton.show()
        }


    }

}