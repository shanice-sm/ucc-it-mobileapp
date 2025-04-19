/*
 * Names: Shanice Smith, David Martin
 * Student ID: 20232188,
 * Submission Due: April 19, 2025
 */


package com.example.uccmobileapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.isGone
import androidx.core.net.toUri
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    // UI elememts
    private lateinit var fabEmail: FloatingActionButton
    private lateinit var btnStaffDirectory: Button
    private lateinit var btnCourses: Button
    private lateinit var btnAdmissions: Button
    private lateinit var searchIcon: ImageView
    private lateinit var searchBar: AutoCompleteTextView
    private lateinit var socialMediaWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        socialMediaWebView = findViewById(R.id.socialMediaWebView)

        socialMediaWebView.webViewClient = WebViewClient()  // Ensures content loads in the WebView
        socialMediaWebView.settings.domStorageEnabled = true
        socialMediaWebView.reload()
        socialMediaWebView.settings.domStorageEnabled = true
        socialMediaWebView.settings.useWideViewPort = true
        socialMediaWebView.settings.loadWithOverviewMode = true
        socialMediaWebView.settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"

        val facebookIcon = findViewById<ImageView>(R.id.facebookIcon)
        val instagramIcon = findViewById<ImageView>(R.id.instagramIcon)
        val twitterIcon = findViewById<ImageView>(R.id.twitterIcon)

        fabEmail = findViewById(R.id.fab_email)

        // Floating Action Button for Email
        fabEmail.setOnClickListener {
            Toast.makeText(this, "Email HOD clicked!", Toast.LENGTH_SHORT).show()
            sendEmail()
        }

        btnStaffDirectory = findViewById(R.id.btnStaffDirectory)

        // Staff Directory Button
        btnStaffDirectory.setOnClickListener {
            startActivity(Intent(this, StaffDirectoryActivity::class.java))
        }

        btnCourses = findViewById(R.id.btnCourses)

        btnCourses.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }

        btnAdmissions = findViewById(R.id.btnAdmissions)

        btnAdmissions.setOnClickListener {
            startActivity(Intent(this, AdmissionsActivity::class.java))
        }

        searchIcon = findViewById(R.id.searchIcon)
        searchBar = findViewById(R.id.searchBar)

        // open social media links in webview when icons are clicked
        facebookIcon.setOnClickListener {
            loadSocialMediaPage("https://m.facebook.com/uccjamaica/")
        }

        instagramIcon.setOnClickListener {
            loadSocialMediaPage("https://www.instagram.com/uccjamaica/")
        }

        twitterIcon.setOnClickListener {
            loadSocialMediaPage("https://twitter.com/UCCjamaica")
        }

        val suggestions = arrayOf(
            "Twitter", "Instagram", "Facebook", "Admissions", "Staff Directory", "Courses"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
        searchBar.setAdapter(adapter)

        searchIcon.setOnClickListener {
            if (searchBar.isGone) {
                searchBar.visibility = View.VISIBLE
                searchBar.animate().alpha(1f).setDuration(300).start()
                searchBar.requestFocus()
            } else {
                searchBar.animate().alpha(0f).setDuration(300).withEndAction {
                    searchBar.visibility = View.GONE
                }.start()
            }
        }

        searchBar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                val query = searchBar.text.toString().trim()
                performSearch(query)
                true
            } else {
                false
            }
        }

        // Search Bar Visibility
        searchIcon.setOnClickListener {
            if (searchBar.isGone) {
                searchBar.visibility = View.VISIBLE
                searchBar.animate().alpha(1f).setDuration(300).start()
                searchBar.requestFocus()
            } else {
                searchBar.animate().alpha(0f).setDuration(300).withEndAction {
                    searchBar.visibility = View.GONE
                }.start()
            }
        }

        // Search Bar Action (Enter/Done Key)
        searchBar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                val query = searchBar.text.toString().trim()
                performSearch(query)
                true
            } else {
                false
            }
        }

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun sendEmail() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("hod@example.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Inquiry")
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performSearch(query: String) {
        val normalizedQuery = query.trim().lowercase()
        try {
            when (normalizedQuery) {
                "twitter" -> {
                    val twitterIntent =
                        packageManager.getLaunchIntentForPackage("com.twitter.android")
                    if (twitterIntent != null) {
                        startActivity(twitterIntent)
                    } else {
                        Toast.makeText(this, "Twitter app not installed", Toast.LENGTH_SHORT).show()
                    }
                }

                "instagram" -> {
                    val instagramIntent =
                        packageManager.getLaunchIntentForPackage("com.instagram.android")
                    if (instagramIntent != null) {
                        startActivity(instagramIntent)
                    } else {
                        Toast.makeText(this, "Instagram app not installed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                "facebook" -> {
                    val facebookIntent =
                        packageManager.getLaunchIntentForPackage("com.facebook.katana")
                    if (facebookIntent != null) {
                        startActivity(facebookIntent)
                    } else {
                        Toast.makeText(this, "Facebook app not installed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                "admissions" -> {
                    val admissionsIntent = Intent(
                        Intent.ACTION_VIEW,
                        "https://ucc.edu.jm/admissions".toUri()
                    )
                    startActivity(admissionsIntent)
                }

                "courses" -> {
                    val coursesIntent = Intent(this, CoursesActivity::class.java)
                    startActivity(coursesIntent)
                }

                "staff directory" -> {
                    val staffIntent = Intent(this, StaffDirectoryActivity::class.java)
                    startActivity(staffIntent)
                }

                else -> {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, "https://www.$query.com".toUri())
                    startActivity(browserIntent)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadSocialMediaPage(url: String) {
        socialMediaWebView.apply {
            visibility = View.VISIBLE
            clearCache(true)
            clearHistory()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.setSupportMultipleWindows(true)
            settings.javaScriptCanOpenWindowsAutomatically = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: android.webkit.WebResourceRequest?): Boolean {
                    return false  // Ensures links open inside the WebView
                }
            }
            loadUrl(url)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (socialMediaWebView.isVisible && socialMediaWebView.canGoBack()) {
            socialMediaWebView.goBack()  // Navigate backward in the WebView
        } else if (socialMediaWebView.isVisible) {
            socialMediaWebView.visibility = View.GONE  // Hide the WebView and go back to the main layout
        } else {
            super.onBackPressed()
        }
    }
}