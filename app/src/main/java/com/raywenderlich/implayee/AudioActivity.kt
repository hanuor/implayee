package com.raywenderlich.implayee

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class AudioActivity : AppCompatActivity() {
    private var playPauseAudio: Button? = null
    private var restartAudio: Button? = null
    private var mediaPlayer: MediaPlayer? = null
    private var stoppageTime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        mediaPlayer = MediaPlayer()
        //Setting up the mediaPlayer? to stream Music
        val audioDataSource =
                "android.resource://com.raywenderlich.implayee/" + R.raw.sample_audio
        mediaPlayer?.setDataSource(this, Uri.parse(audioDataSource))
        mediaPlayer?.prepare()

        initViews()
        onClicks()

    }

    private fun initViews() {
        //play / pause audio button (mediaPlayer? class)
        playPauseAudio = findViewById(R.id.playPauseAudio)

        //restart Audio button
        restartAudio = findViewById(R.id.restartAudio)
        playPauseAudio?.text = this.getString(R.string.audio_play)

    }

    private fun onClicks() {
        //on click operations
        playPauseAudio?.setOnClickListener {

            if (mediaPlayer?.isPlaying as Boolean) {
                mediaPlayer?.pause()
                playPauseAudio?.text = this.getString(R.string.audio_play)
            } else {
                mediaPlayer?.start()
                playPauseAudio?.text = this.getString(R.string.audio_pause)

                mediaPlayer?.setOnCompletionListener {
                    playPauseAudio?.text = this.getString(R.string.audio_play)
                }
            }


        }
        restartAudio?.setOnClickListener {
            mediaPlayer?.seekTo(0)
            mediaPlayer?.start()
            playPauseAudio?.text = this.getString(R.string.audio_pause)
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        //getting current position (integer) of mediaplayer
        stoppageTime = mediaPlayer?.currentPosition as Int
        playPauseAudio?.text = this.getString(R.string.audio_play)
    }

    override fun onResume() {
        super.onResume()
        if (stoppageTime > 0) {
            //seeking to the time when onPause() was called
            mediaPlayer?.seekTo(stoppageTime)
            mediaPlayer?.start()
            playPauseAudio?.text = this.getString(R.string.audio_pause)
        }
    }


}