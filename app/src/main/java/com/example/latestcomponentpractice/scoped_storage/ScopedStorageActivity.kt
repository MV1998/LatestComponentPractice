package com.example.latestcomponentpractice.scoped_storage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityScopedStorageBinding
import com.example.latestcomponentpractice.scoped_storage.api_service.ImageAPI
import com.example.latestcomponentpractice.scoped_storage.api_service.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ScopedStorageActivity : AppCompatActivity() {
    private val binding : ActivityScopedStorageBinding by lazy {
        ActivityScopedStorageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var client = RetrofitClient.getClient()
        val imageService = client.create(ImageAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = imageService.getImage("J73Ev-qAviEMGxzJQzBBiWV5JEbqVhlcsUhL6Oc5pgE")
//            Log.d(javaClass.simpleName, "onCreate: ${result.body()}")
            result.body()?.let {
                Log.d(javaClass.simpleName, "onCreate: ${it.urls.full}")
                val url = URL(it.urls.full)
                val connect : HttpURLConnection = url.openConnection() as HttpURLConnection
                connect.doOutput = true
                connect.connect()
                val stream = connect.inputStream
                val bitmap: Bitmap? = BitmapFactory.decodeStream(stream)
//                val bitmap2 = Bitmap.createScaledBitmap(bitmap!!, 100, 100, true)

//                openFileOutput("file.jpeg", MODE_PRIVATE).use { stream ->
//                    if (bitmap2.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
//                        Log.d(javaClass.simpleName, "onCreate: images has been saved.")
//                    }
//                }
//                Log.d(javaClass.simpleName, "onCreate: ${filesDir.absolutePath}")
//                filesDir.listFiles().forEach {
//                    Log.d(javaClass.simpleName, "onCreate: ${it.absolutePath}")
//                }

                launch(Dispatchers.Main) {
//                    binding.testingImageView.setImageBitmap(bitmap)
                    Glide.with(this@ScopedStorageActivity)
                        .load(bitmap)
                        .into(binding.testingImageView)
                    Log.d(javaClass.simpleName, "onCreate: should update now")
                }
            }
        }

        binding.launcherModesBtn.setOnClickListener {
            val intent = Intent(this@ScopedStorageActivity, ScopedStorageActivity::class.java)
            startActivity(intent)
        }
    }

    fun savePhotoToInternalStorage(fileName : String, bmp : Bitmap) : Boolean {
        return try {
            // create a file and gives the output stream to write the image data in it.
            openFileOutput("$fileName.jpg", MODE_PRIVATE).use {
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 98, it)) {
                    throw IOException("Couldn't save bitmap.")
                }
            }
            true
        }catch (e: IOException) {
            false
        }
    }

    fun retrievePhotosFromInternalStorage() {

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(javaClass.simpleName, "onNewIntent: ")
    }
}