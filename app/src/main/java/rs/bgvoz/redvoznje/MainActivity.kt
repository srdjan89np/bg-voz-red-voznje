package rs.bgvoz.redvoznje

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var adView: AdView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }

        // Red voznje (lokalni HTML iz assets/index.html, radi i bez interneta)
        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true   // omiljene relacije (localStorage)
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, req: WebResourceRequest): Boolean {
                    val url = req.url
                    if (url.scheme == "file") return false
                    startActivity(Intent(Intent.ACTION_VIEW, url))  // spoljni linkovi u browser
                    return true
                }
            }
            loadUrl("file:///android_asset/index.html")
        }
        root.addView(webView, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f))

        // AdMob baner na dnu ekrana
        val adContainer = FrameLayout(this)
        root.addView(adContainer, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        setContentView(root)

        MobileAds.initialize(this) {}
        adContainer.post { loadBanner(adContainer) }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) webView.goBack() else finish()
            }
        })
    }

    private fun loadBanner(container: FrameLayout) {
        val density = resources.displayMetrics.density
        val widthDp = (container.width.takeIf { it > 0 } ?: resources.displayMetrics.widthPixels) / density
        val ad = AdView(this).apply {
            adUnitId = getString(R.string.admob_banner_id)
            setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this@MainActivity, widthDp.toInt()))
        }
        container.addView(ad)
        ad.loadAd(AdRequest.Builder().build())
        adView = ad
    }

    override fun onPause() { adView?.pause(); super.onPause() }
    override fun onResume() { super.onResume(); adView?.resume() }
    override fun onDestroy() { adView?.destroy(); webView.destroy(); super.onDestroy() }
}
