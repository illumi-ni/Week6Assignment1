package com.ujjallamichhane.week6assignment1.ui.aboutus

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ujjallamichhane.week6assignment1.R


class AboutUsFragment : Fragment() {

    private lateinit var notificationsViewModel: AboutUsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about_us, container, false)

        val webView: WebView = root.findViewById(R.id.webView)
        val layout: ConstraintLayout = root.findViewById(R.id.layout)

        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            loadWebPage(root.context, layout, webView)
        })
        return root
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return (network != null)
    }

    private fun loadWebPage(context: Context, layout: ConstraintLayout, webView: WebView) {
        if (isNetworkAvailable(context)) {

            val url = "https://softwarica.edu.np/"
            webView.loadUrl(url)
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
        } else {
            val snackbar = Snackbar
                    .make(layout, "No Internet Connection", Snackbar.LENGTH_LONG)
                    .setAction("Reload") {
                        loadWebPage(context, layout, webView)
                    }
            snackbar.show()
        }
    }
}