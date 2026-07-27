package edu.vt.cs5254.fancygallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.vt.cs5254.fancygallery.databinding.FragmentPhotoPageBinding

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

class PhotoPageFragment : Fragment() {
    private val args: PhotoPageFragmentArgs by navArgs() // 4.18 Q&A & 23.10
    private var _binding: FragmentPhotoPageBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "FPPB is null!" }

    @SuppressLint("SetJavaScriptEnabled") // 23.10
    // 23.5  Setting up your web browser fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoPageBinding.inflate(inflater, container, false)

        // 4.18 Q&A & 23.10
        binding.apply {
            progressBar.max = 100 // 23.11  Using WebChromeClient
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(args.photoPageUri.toString())

                // 23.11  Using WebChromeClient
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {

                        if (newProgress == 100) {
                            progressBar.visibility = View.GONE
                        } else {
                            progressBar.visibility = View.VISIBLE
                            progressBar.progress = newProgress
                        }
                    }

                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        val parent = requireActivity() as AppCompatActivity
                        parent.supportActionBar?.subtitle = title
                    }
                }
            }
        }
        return binding.root // 23.5
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val parent = requireActivity() as AppCompatActivity
        parent.supportActionBar?.subtitle = null

        _binding = null
    }
}