package edu.vt.cs5254.dreamcatcher

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.MenuProvider
import androidx.core.view.doOnLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.vt.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import java.io.File

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
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
        DreamDetailViewModelFactory(args.dreamId)
    }
    private val photoLauncher = // 3.29 Q&A Take photo
        registerForActivityResult(ActivityResultContracts.TakePicture()) { tookPicture ->
            Log.w("DDF!!!", "Took picture? $tookPicture")
            if (tookPicture) {
                ddvm.dream.value?.let { dream ->
                    binding.dreamPhoto.tag = null
                    updatePhoto(dream)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentDreamDetailBinding.inflate(inflater, container, false)
        // 4.4 Q&A
        binding.dreamEntryRecycler.layoutManager = LinearLayoutManager(context)
        getItemTouchHelper().attachToRecyclerView(binding.dreamEntryRecycler)
        // 3.29 Q&A Take photo
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_dream_detail, menu)
                // below -> does not need to be done for the share dream portion
                // intent to launch photoLauncher -- if no camera app available...

                if (photoLauncher.contract.createIntent(
                        requireContext(),
                        Uri.EMPTY)
                        .resolveActivity(requireActivity().packageManager) == null
                ) {
                    menu.findItem(R.id.take_photo_menu).isVisible = false
                } // ...hide the take_photo_menu item
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {

                    R.id.share_dream_menu -> {
                        ddvm.dream.value?.let {
                            shareDream(it) // uses private helper below onDestroyView
                        }
                        true
                    }


                    // 3.29 Q&A Take photo
                    R.id.take_photo_menu -> {
                        // access dream StateFlow in ddvm in order to access photoFileName
                        ddvm.dream.value?.let {
                            val photoFile = File(
                                requireContext().applicationContext.filesDir,
                                it.photoFileName
                            )
                            val photoUri = FileProvider.getUriForFile(
                                requireContext(),
                                "edu.vt.cs5254.dreamcatcher.fileprovider",
                                photoFile
                            )
                            photoLauncher.launch(photoUri)
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 13.19
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ddvm.dream.collect { dream ->
                    dream?.let { updateUi(it) }
                }
            }
        }
        // set listeners...
        // 3.29 Q&A Take photo
        binding.dreamPhoto.setOnClickListener {
            ddvm.dream.value?.let { dream ->
                findNavController().navigate(
                    DreamDetailFragmentDirections.showPhotoDetail(
                        dream.photoFileName
                    )
                )
            }
        }
        // 13.21
        binding.deferredCheckbox.setOnClickListener {
            ddvm.updateDream { oldDream ->
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
        // 13.21
        binding.fulfilledCheckbox.setOnClickListener {
            ddvm.updateDream { oldDream ->
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
        // 13.21
        binding.apply {
            titleText.doOnTextChanged { text, _, _, _ ->
                ddvm.updateDream { oldDream ->
                    oldDream.copy(title = text.toString()).apply {
                        entries = oldDream.entries
                    }
                }
            }
        }
        // In onViewCreated(), call fab.setOnClickListener() to navigate to the dialog.
        binding.addReflectionButton.setOnClickListener {
            findNavController().navigate(
                DreamDetailFragmentDirections.addReflection()
            )
        }
        // 14.8
        setFragmentResultListener(
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

    // 16.7 adding getDreamReport
    private fun getDreamReport(dream: Dream): String {
    // 1) dream_report_header: a title and a date
        val titleString = getString(
            R.string.dream_report_header,
            dream.title,
            getString(
                R.string.last_updated_text, // <-- an Int
                DateFormat.format("yyyy-MM-dd 'at' hh:mm:ss a", dream.lastUpdated) // <-- a String
            ))
    // 2) the dream_report_reflections (complex and optional)
        val reflectionString = if (dream.entries.any { it.kind == DreamEntryKind.REFLECTION } ) {
            getString(
                R.string.dream_report_reflections,
                dream.entries
                    .filter { it.kind == DreamEntryKind.REFLECTION }
                    .joinToString(prefix = " *", separator = " *") { entry ->
                        " ${entry.text}\n"
                    }
            )
        } else ""
    // 3) the dream_report_status (simple but optional)
        val statusString = if (dream.isFulfilled || dream.isDeferred) {
            getString(
                R.string.dream_report_status,
                if (dream.isDeferred) {
                    getString(R.string.deferred_checkbox)
                } else if (dream.isFulfilled) {
                    getString(R.string.fulfilled_checkbox)
                } else ""
            )
        } else ""
        // the overall share below
        return getString(
            R.string.dream_report,
            // below are the strings that replace the placeholders in strings.xml
            titleString, reflectionString, statusString
        )
    }

    // 16.8 Sharing a dream via menu bar --> private helper
    @DelicateCoroutinesApi
    private fun shareDream(dream: Dream) {
        val reportIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getDreamReport(dream))
            putExtra(
                Intent.EXTRA_SUBJECT,
                dream.title
               // getString(R.string.title_text)
            )
        }
        // 16.9 using a chooser
        val chooserIntent = Intent.createChooser(
            reportIntent,
            getString(R.string.share_dream)
        )
        startActivity(chooserIntent)
    }

    // 3.29 Q&A
    private fun updatePhoto(dream: Dream) {
        if (tag != dream.photoFileName) {
            val photoFile = File(
                requireActivity().filesDir,
                dream.photoFileName
            )
            // You must enable/disable the ImageView component, based on whether an image is
            // actually present. The code belongs in updatePhoto(), and the if-else structure is already there.

            if (photoFile.exists()) {
                binding.dreamPhoto.doOnLayout { imgView ->
                    val bitmap = getScaledBitmap(
                        photoFile.path,
                        imgView.width,
                        imgView.height
                    )
                    binding.dreamPhoto.setImageBitmap(bitmap)
                    binding.dreamPhoto.tag = dream.photoFileName
                    binding.dreamPhoto.isEnabled = true

                }
            } else {
                binding.dreamPhoto.setImageBitmap(null)
                binding.dreamPhoto.tag = null
                binding.dreamPhoto.isEnabled = false
            }

        }
    }

    // 16.16  Resolving Intents
    private fun canResolveIntent(intent: Intent): Boolean {
        val packageManager: PackageManager = requireActivity().packageManager
        val resolvedActivity: ResolveInfo? =
            packageManager.resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
        return resolvedActivity != null
    }

    @DelicateCoroutinesApi
    private fun getItemTouchHelper(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dreamEntryHolder =
                    viewHolder as DreamEntryHolder // cast viewHolder as DreamHolder object
                val swipedEntry = dreamEntryHolder.boundEntry
                ddvm.updateDream { oldDream ->
                    oldDream.copy().apply {
                        entries = oldDream.entries.filter { it != swipedEntry }
                    }
                }
            }
            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dreamEntryHolder = viewHolder as DreamEntryHolder
                val swipedEntry = dreamEntryHolder.boundEntry
                return if (swipedEntry.kind == DreamEntryKind.REFLECTION) {
                    ItemTouchHelper.LEFT
                } else {
                    0
                }
            }
        })
    }

    private fun updateUi(dream: Dream) {
        // title text
        if (binding.titleText.text.toString() != dream.title) {
            binding.titleText.setText(dream.title)
        }
        // 4.4 Q&A
        binding.dreamEntryRecycler.adapter = DreamEntryAdapter(dream.entries)
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
        updatePhoto(dream) // 3.29 Q&A
    }

}