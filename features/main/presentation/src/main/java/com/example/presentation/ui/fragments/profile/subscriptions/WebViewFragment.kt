package com.example.presentation.ui.fragments.profile.subscriptions

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.view.MotionEvent
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
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

    @SuppressLint("ClickableViewAccessibility")
    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // Обработка кликов по элементам в WebView
            binding.webView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val hitTestResult = (v as WebView).hitTestResult
                if (hitTestResult.type == WebView.HitTestResult.SRC_ANCHOR_TYPE) {
                    val url = hitTestResult.extra
                    println("Link clicked: $url")
                    // Обработайте нажатие на ссылку здесь
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {

        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)

            }
        }
        binding.webView.webChromeClient = object : WebChromeClient() {

        }
        binding.webView.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        binding.webView.settings.setSupportZoom(true)
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