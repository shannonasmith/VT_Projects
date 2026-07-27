package edu.vt.cs5254.dreamcatcher

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.view.doOnLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import edu.vt.cs5254.dreamcatcher.databinding.FragmentPhotoDialogBinding
import java.io.File

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

class PhotoDialogFragment : DialogFragment() {

    private val args: PhotoDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = FragmentPhotoDialogBinding.inflate(layoutInflater)

        val file = File(
            requireActivity().filesDir,
            args.dreamPhotoFilename
        )

        binding.root.doOnLayout { dialogRoot ->
            val bitmap = getScaledBitmap(
                file.path,
                dialogRoot.width,
                dialogRoot.height
            )
            binding.photoDetail.setImageBitmap(bitmap)
        }

        return AlertDialog.Builder(requireContext())
            // super.onCreateDialog(savedInstanceState)
            .setView(binding.root)
            .show()


    }
}