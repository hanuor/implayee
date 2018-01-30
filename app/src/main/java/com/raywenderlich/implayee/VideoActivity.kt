/*
 * Copyright 2018 Google, Inc.
 *
 * ...
 */
package com.raywenderlich.implayee

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast


class VideoActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null
    private var playPauseButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        try {
            mp = MediaPlayer.create(this, R.raw.sample_video)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, this.getString(R.string.play_error), Toast.LENGTH_SHORT).show()
        }
        val sv = findViewById<View>(R.id.surfaceView) as SurfaceView
        playPauseButton = findViewById(R.id.playPauseButton)

        playPauseButton?.setOnClickListener({
            if (mp?.isPlaying as Boolean) {
                mp?.pause()
                playPauseButton?.text = this.getString(R.string.play)
            } else {
                mp?.start()
                playPauseButton?.text = this.getString(R.string.pause)
            }
        })
        val holder = sv.holder
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                mp?.setDisplay(holder)
                mp?.start()
            }

        })

    }

    override fun onPause() {
        super.onPause()
        //Pausing the mediaplayer to so that it can be resumed when user switches back to the app.
        if (null != mp) {
            mp?.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        //Releasing the mediaplayer for saving resources
        mp?.release()
    }
}
