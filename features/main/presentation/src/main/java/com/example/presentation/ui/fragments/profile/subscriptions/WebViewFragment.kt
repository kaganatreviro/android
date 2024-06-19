package com.example.presentation.ui.fragments.profile.subscriptions

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showSimpleDialog
import com.example.presentation.databinding.FragmentWebViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebViewFragment : BaseFragment<FragmentWebViewBinding, SubscriptionsViewModel>() {

    override fun getViewBinding() = FragmentWebViewBinding.inflate(layoutInflater)
    override val viewModel by viewModel<SubscriptionsViewModel>()
    private val args: WebViewFragmentArgs by navArgs()

    @SuppressLint("ClickableViewAccessibility")
    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)

        binding.webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url != null) {
                    if (url.contains("/api/v1/subscription/execute-payment/")){
                        showSimpleDialog("Success", "Оплата прошла успешно!")
                        findNavController().navigate(WebViewFragmentDirections.actionWebViewFragmentToProfileFragment())
                    }else if (url.contains("/api/v1/subscription/cancel-payment/")){
                        showSimpleDialog("Error", "Произошла ошибка!")
                        findNavController().popBackStack()
                    }
                }
            }
        }
        binding.webView.loadUrl(args.Url.approvalUrl)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            findNavController().popBackStack()
        }
    }
}