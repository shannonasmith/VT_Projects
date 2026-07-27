package edu.vt.cs5254.dreamcatcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.cs5254.dreamcatcher.databinding.ListItemDreamBinding

/**
 * CS 5254 Project 2A - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.26
 */

class DreamHolder(private val binding: ListItemDreamBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dream: Dream) {

        binding.listItemTitle.text = dream.title

        val reflectionCount = dream.entries.count { it.kind == DreamEntryKind.REFLECTION }
        binding.root.context.getString(R.string.list_item_reflection_count, reflectionCount)
            .also { binding.listItemReflectionCount.text = it }

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
    }
}

class DreamListAdapter(private val dreamList: List<Dream>) : RecyclerView.Adapter<DreamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemDreamBinding = ListItemDreamBinding.inflate(inflater, parent, false)
        return DreamHolder(binding)
    }

    override fun onBindViewHolder(holder: DreamHolder, position: Int) {
        holder.bind(dreamList[position])
    }

    override fun getItemCount() = dreamList.size
}