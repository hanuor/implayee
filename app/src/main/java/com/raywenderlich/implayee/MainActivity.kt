/*
 * Copyright 2018 Google, Inc.
 *
 * ...
 */
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

    fun initViews() {
        //play audio button (mediaplayer class)
        playAudioButton = findViewById(R.id.playAudioButton)
        //play video button (mediaplayer class)
        playVideoButton = findViewById(R.id.playVideoButton)
        //play video using ExoPlayer
        exoPlayerButton = findViewById(R.id.exoPlayerButton)
    }

    fun onClicks() {
        playAudioButton?.setOnClickListener({
            var triggerAudio = Intent(this, AudioActivity::class.java)
            startActivity(triggerAudio)
        })
        playVideoButton?.setOnClickListener({
            var triggerVideo = Intent(this, VideoActivity::class.java)
            startActivity(triggerVideo)
        })
        exoPlayerButton?.setOnClickListener({
            var triggerExo = Intent(this, ExoPlayerActivity::class.java)
            startActivity(triggerExo)
        })
    }
}
