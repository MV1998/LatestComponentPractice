package com.example.latestcomponentpractice.scoped_storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityScopedStorageBinding
import com.example.latestcomponentpractice.scoped_storage.api_service.ImageAPI
import com.example.latestcomponentpractice.scoped_storage.api_service.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class ScopedStorageActivity : AppCompatActivity() {

    private val binding : ActivityScopedStorageBinding by lazy {
        ActivityScopedStorageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoped_storage)


        var client = RetrofitClient.getClient()
        val imageService = client.create(ImageAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = imageService.getImage("J73Ev-qAviEMGxzJQzBBiWV5JEbqVhlcsUhL6Oc5pgE")
//            Log.d(javaClass.simpleName, "onCreate: ${result.body()}")
            result.body()?.let {
                Log.d(javaClass.simpleName, "onCreate: ${it.urls.full}")
                val url = URL("https://images.unsplash.com/photo-1620163280053-68782bd98475?crop=entropy&cs=srgb&fm=jpg&ixid=M3w1OTA1NjB8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MTMxMTc1Nzl8&ixlib=rb-4.0.3&q=85")
                val connect : HttpURLConnection = url.openConnection() as HttpURLConnection
                connect.doOutput = true
                connect.connect()
                Log.d(javaClass.simpleName, "onCreate: ${connect.inputStream}")
                val stream = connect.inputStream
                val bmOptions = BitmapFactory.Options()
                // Decode InputStream into Bitmap
                val bitmap: Bitmap? = BitmapFactory.decodeStream(stream)
                val bitmap2 = Bitmap.createScaledBitmap(bitmap!!, 600, 600, true)
                withContext(Dispatchers.Main) {
                    binding.testingImageView.setImageBitmap(bitmap2)
                    Log.d(javaClass.simpleName, "onCreate: should update now")
                }
            }
        }
    }
}