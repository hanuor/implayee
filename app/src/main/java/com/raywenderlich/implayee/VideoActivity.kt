package com.raywenderlich.implayee

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button


class VideoActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var playPauseButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        //instance of mediaplayer
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_video)
        //initialization
        val sv = findViewById<View>(R.id.surfaceView) as SurfaceView
        playPauseButton = findViewById(R.id.playPauseButton)

        //play and pause button functionality
        playPauseButton?.setOnClickListener {
            if (mediaPlayer?.isPlaying as Boolean) {
                mediaPlayer?.pause()
                playPauseButton?.text = this.getString(R.string.play)
            } else {
                mediaPlayer?.start()
                playPauseButton?.text = this.getString(R.string.pause)
            }
        }
        val holder = sv.holder

        //Callback of Surface view holder.
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                mediaPlayer?.setDisplay(holder)
                mediaPlayer?.start()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        //Pausing the mediaplayer to so that it can be resumed when user switches back to the app.
        if (null != mediaPlayer) {
            mediaPlayer?.pause()

        }
    }

    override fun onResume() {
        super.onResume()

        //seeking to the stored position
        mediaPlayer?.seekTo(0)
        mediaPlayer?.start()
        playPauseButton?.text = this.getString(R.string.pause)


    }


    override fun onDestroy() {
        super.onDestroy()
        //Releasing the mediaplayer for saving resources
        mediaPlayer?.release()
    }

}
