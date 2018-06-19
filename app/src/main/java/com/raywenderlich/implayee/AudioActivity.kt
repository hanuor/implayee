/*
 * Copyright 2018 Google, Inc.
 *
 * ...
 */
package com.raywenderlich.implayee

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class AudioActivity : AppCompatActivity() {
    private var playPauseAudio: Button? = null
    private var restartAudio: Button? = null
    private val mp = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        //Setting up the mediaplayer to stream Music
        val audioDataSource =
                "android.resource://com.raywenderlich.implayee/" + R.raw.sample_audio
        try {
            mp.setDataSource(this, Uri.parse(audioDataSource))
            mp.prepare()
            mp.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        initViews()
        onClicks()

    }

    private fun initViews() {
        //play / pause audio button (mediaplayer class)
        playPauseAudio = findViewById(R.id.playPauseAudio)

        //restart Audio button
        restartAudio = findViewById(R.id.restartAudio)
    }

    private fun onClicks() {
        //on click operations
        playPauseAudio?.setOnClickListener({
            if (mp.isPlaying) {
                mp.pause()
                playPauseAudio?.text = this.getString(R.string.audio_play)
            } else {
                mp.start()
                playPauseAudio?.text = this.getString(R.string.audio_pause)
            }

        })
        restartAudio?.setOnClickListener({
            mp.seekTo(0)
            mp.start()
            playPauseAudio?.text = "Pause Audio"
        })
    }
}