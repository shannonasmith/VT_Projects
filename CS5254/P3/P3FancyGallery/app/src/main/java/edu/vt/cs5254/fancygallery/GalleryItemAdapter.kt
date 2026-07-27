package edu.vt.cs5254.fancygallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import edu.vt.cs5254.fancygallery.api.GalleryItem
import edu.vt.cs5254.fancygallery.databinding.ListItemGalleryBinding

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

class GalleryItemHolder(
    private val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    // Testing
    lateinit var boundGalleryItem: GalleryItem
        private set

    fun bind(galleryItem: GalleryItem, onItemClicked: (Uri) -> Unit) { // 4.18 Q&A & 23.2
        boundGalleryItem = galleryItem // Testing
        // 20.35  Loading the image
        binding.itemImageView.load(galleryItem.url) {
            // 20.36  Loading the image
            placeholder(R.drawable.ic_placeholder)
            diskCachePolicy(CachePolicy.DISABLED)
        }
        // 4.18 Q&A & BNRG 23.2  Firing an implicit intent when an item is pressed
        binding.root.setOnClickListener {
            onItemClicked(galleryItem.photoPageUri)
        }
    }
}

class GalleryItemAdapter(
    private val galleryItems: List<GalleryItem>,
    // 4.18 Q&A & BNRG 23.3  Binding PhotoViewHolder
    private val onItemClicked: (Uri) -> Unit

) : RecyclerView.Adapter<GalleryItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return GalleryItemHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryItemHolder, position: Int) {
        holder.bind(galleryItems[position], onItemClicked)
    }

    override fun getItemCount() = galleryItems.size
}