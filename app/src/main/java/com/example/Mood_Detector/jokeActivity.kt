package com.example.Mood_Detector

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_joke.*
import kotlinx.android.synthetic.main.activity_name.*

class jokeActivity : AppCompatActivity() {

    var currentImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        loadMeme()
    }

    private fun loadMeme(){
        // Instantiate the RequestQueue.
        progressBar.visibility = View.VISIBLE

        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    currentImageUrl = response.getString("url")
                    Glide.with(this).load(currentImageUrl).listener(object : RequestListener<Drawable>{

                        override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?, target: Target<Drawable>?,
                                isFirstResource: Boolean): Boolean {

                                    progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                                resource: Drawable?, model: Any?,
                                target: Target<Drawable>?, dataSource: DataSource?,
                                isFirstResource: Boolean): Boolean {

                            progressBar.visibility = View.GONE
                            return false

                        }
                    }).into(memeImageView)
                },
                Response.ErrorListener {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "Text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Check out this meme $currentImageUrl")
        val chooser = Intent.createChooser(intent, "Share this meme using.")
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        loadMeme()
    }
}