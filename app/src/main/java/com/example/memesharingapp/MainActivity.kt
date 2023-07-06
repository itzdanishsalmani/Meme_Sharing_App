package com.example.memesharingapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
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


class MainActivity : AppCompatActivity() {

    var currentImageUrl : String? = null //object for url to share and access across the App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call the loadMeme() function to load the meme image
        loadMeme()
    }

    private fun loadMeme(){
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = View.VISIBLE //progressBar visibility

        val imageView = findViewById<ImageView>(R.id.imageView)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        val url = "https://meme-api.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url ,null,
            Response.Listener { response ->
                currentImageUrl = response.getString("url")

                Glide.with(this).load(url).listener(object : RequestListener<Drawable>{
                    //RequestListener is an interface

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                }).into(imageView)
            },
            Response.ErrorListener { "Check Internet Connection "})

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

     fun nextMeme(view: View) {
         loadMeme()
     }
     fun shareMeme(view: View) {


     }
}
