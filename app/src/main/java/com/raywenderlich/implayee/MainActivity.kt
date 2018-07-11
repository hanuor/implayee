package com.raywenderlich.implayee

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var playAudioButton: Button? = null
    private var playVideoButton: Button? = null
    private var exoPlayerButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        onClicks()
    }

    private fun initViews() {
        //play audio button (mediaplayer class)
        playAudioButton = findViewById(R.id.playAudioButton)
        //play video button (mediaplayer class)
        playVideoButton = findViewById(R.id.playVideoButton)
        //play video using ExoPlayer
        exoPlayerButton = findViewById(R.id.exoPlayerButton)
    }

    private fun onClicks() {
        playAudioButton?.setOnClickListener {
            val triggerAudio = Intent(this, AudioActivity::class.java)
            startActivity(triggerAudio)
        }
        playVideoButton?.setOnClickListener {
            val triggerVideo = Intent(this, VideoActivity::class.java)
            startActivity(triggerVideo)
        }
        exoPlayerButton?.setOnClickListener {
            val triggerExo = Intent(this, ExoPlayerActivity::class.java)
            startActivity(triggerExo)
        }
    }
}
