package com.example.presentation.ui.fragments.profile.subscriptions

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core_ui.base.BaseFragment
import com.example.presentation.databinding.FragmentWebViewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WebViewFragment : BaseFragment<FragmentWebViewBinding, SubscriptionsViewModel>() {

    override val viewModel by activityViewModel<SubscriptionsViewModel>()
    override fun getViewBinding() = FragmentWebViewBinding.inflate(layoutInflater)
    private val args: WebViewFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {

        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(args.Url)
        binding.webView.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        binding.webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}