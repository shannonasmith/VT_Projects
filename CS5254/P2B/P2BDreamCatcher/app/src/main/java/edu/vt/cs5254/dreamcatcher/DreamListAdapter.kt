package edu.vt.cs5254.dreamcatcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.cs5254.dreamcatcher.databinding.ListItemDreamBinding
import java.util.*

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

class DreamHolder(private val binding: ListItemDreamBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dream: Dream, onDreamClicked: (dreamId: UUID) -> Unit) { // 13.6 + 13.12
        binding.listItemTitle.text = dream.title

        val reflectionCount = dream.entries.count {
            it.kind == DreamEntryKind.REFLECTION
        }
        binding.root.context.getString(
            R.string.list_item_reflection_count, reflectionCount
        )
            .also {
                binding.listItemReflectionCount.text = it
            }

        with(binding.listItemImage) {
            when {
                dream.isDeferred -> {
                    visibility = View.VISIBLE
                    setImageResource(R.drawable.dream_deferred_icon)
                }
                dream.isFulfilled -> {
                    visibility = View.VISIBLE
                    setImageResource(R.drawable.dream_fulfilled_icon)
                }
                else -> {
                    visibility = View.GONE
                }
            }
        }
        binding.root.setOnClickListener {
            onDreamClicked(dream.id) // 13.6 + 13.12
        }

    }
}

class DreamListAdapter(
    private val dreamList: List<Dream>,
    private val onDreamClicked: (dreamId: UUID) -> Unit // 13.7 + 13.12
) :
    RecyclerView.Adapter<DreamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemDreamBinding = ListItemDreamBinding.inflate(inflater, parent, false)
        return DreamHolder(binding)
    }

    override fun onBindViewHolder(holder: DreamHolder, position: Int) {
        holder.bind(dreamList[position], onDreamClicked) // 13.12
    }

    override fun getItemCount() = dreamList.size
}