package edu.vt.cs5254.fancygallery

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.imageLoader
import edu.vt.cs5254.fancygallery.databinding.FragmentGalleryBinding
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "FGBinding is null" }

    // 20.29  Getting a ViewModel instance from the provider
    private val galleryVM: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_gallery, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.reload_menu -> {
                        // clear cache
                        requireContext().imageLoader.memoryCache?.clear()
                        // reload items
                        galleryVM.reloadGalleryItems()
                        // done
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)

        return binding.root
    }

    // BNRG 20.7  Using the Retrofit object to create an instance of the API
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // BNRG 20.10  Making a network request
        viewLifecycleOwner.lifecycleScope.launch {
            // BNRG 20.29  Getting a ViewModel instance from the provider
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                galleryVM.galleryItems.collect { items ->
                    Log.w("GF!!!", "Items: $items")
                    // BNRG 20.33  Adding an adapter to the recycler view when data is available or changed
                    binding.photoGrid.adapter = GalleryItemAdapter(items) { photoPageUri ->
                        // 23.9  Switching to launch your activity/browser
                        findNavController().navigate(
                            GalleryFragmentDirections.showPhoto(
                                photoPageUri
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