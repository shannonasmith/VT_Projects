package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import edu.vt.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding

/**
 * CS 5254 Project 2A - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.26
 */

class DreamDetailFragment : Fragment() {

    private val ddvm: DreamDetailViewModel by viewModels()

    private var _binding: FragmentDreamDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentDreamDetailBinding is null!"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)


        return binding.root
    }

    // set listeners using regular binding, not _binding --> binding.whatever
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deferredCheckbox.setOnClickListener {
            if (ddvm.dream.isDeferred) {
                ddvm.dream.entries = ddvm.dream.entries.dropLast(1)
            } else {
                ddvm.dream.entries += DreamEntry(
                    kind = DreamEntryKind.DEFERRED,
                    dreamId = ddvm.dream.id)
            }
            updateView()
        }

        binding.fulfilledCheckbox.setOnClickListener {
            if (ddvm.dream.isFulfilled) {
                ddvm.dream.entries = ddvm.dream.entries.dropLast(1)
            } else {
                ddvm.dream.entries += DreamEntry(
                    kind = DreamEntryKind.FULFILLED,
                    dreamId = ddvm.dream.id
                )
            }
            updateView()
        }

        // to make copy of dream entry title and change text
        binding.apply {
            titleText.doOnTextChanged { text, _, _, _ ->
                ddvm.dream = ddvm.dream.copy(title = text.toString()).apply {
                    entries = ddvm.dream.entries
                }
            }
        }

        updateView()
    }

    // bc fragment can outlive the view
    override fun onDestroyView() {
        super.onDestroyView()
        // only used when inflating and destroying view, setting it back to null
        _binding = null
    }

    /*
    @240: You need to update the state of basically every component that could possibly change
    when viewing any dream. This includes:
        ✓ title text
        ✓ enabled/checked state of both checkboxes
        ✓ last updated date
        ✓ each button text/color/visibility
     */
    private fun updateView() {
        // each button text/color/visibility
        val entryButtonList = listOf(
            binding.entry0Button,
            binding.entry1Button,
            binding.entry2Button,
            binding.entry3Button,
            binding.entry4Button)

        entryButtonList.forEach { it.visibility = View.GONE }

        entryButtonList.zip(ddvm.dream.entries).forEach { (button, entry) ->
            button.configureForEntry(entry)
        }

        // title text
        binding.titleText.setText(ddvm.dream.title)

        // last updated date
        val dateFormat = DateFormat.format("yyyy-MM-dd 'at' hh:mm:ss a", ddvm.dream.lastUpdated)
        val dateString = getString(R.string.last_updated_text, dateFormat)
        binding.lastUpdatedText.text = dateString

        // enabled/checked state of both checkboxes --> deferred should start as checked
        binding.deferredCheckbox.isChecked = ddvm.dream.isDeferred
        binding.deferredCheckbox.isEnabled = !ddvm.dream.isFulfilled
        binding.fulfilledCheckbox.isChecked = ddvm.dream.isFulfilled
        binding.fulfilledCheckbox.isEnabled = !ddvm.dream.isDeferred
    }

}