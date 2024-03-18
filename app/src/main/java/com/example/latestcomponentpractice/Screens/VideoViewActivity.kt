package com.example.latestcomponentpractice.Screens

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityVideoViewBinding


class VideoViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVideoViewBinding
    private lateinit var holder : SurfaceHolder
    private lateinit var mediaPlayer: MediaPlayer
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            holder = surfaceView.holder
            mediaPlayer = MediaPlayer()
//            mediaPlayer.setAudioAttributes(
//                AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
//                    .build()
//            )

            var anotherPlayer = MediaPlayer.create(this@VideoViewActivity,R.raw.mohit)
            anotherPlayer.setOnPreparedListener {
                it.start()
            }

            holder.addCallback(object : SurfaceHolder.Callback{
                override fun surfaceCreated(holder: SurfaceHolder) {
                    Log.d(TAG, "surfaceCreated: ")
                    mediaPlayer.setDisplay(holder)
                    mediaPlayer.setDataSource(this@VideoViewActivity, Uri.parse("https://sample-videos.com/video321/mp4/720/big_buck_bunny_720p_1mb.mp4"))
                    mediaPlayer.prepareAsync()
                    mediaPlayer.setOnPreparedListener {
                        progressBar.visibility = View.GONE
                        Log.d(TAG, "surfaceCreated: started")
                       // it.start()
                    }
                }

                override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
                    Log.d(TAG, "surfaceChanged: ")
                }

                override fun surfaceDestroyed(p0: SurfaceHolder) {
                    Log.d(TAG, "surfaceDestroyed: ")
                }

            })
        }
    }
}