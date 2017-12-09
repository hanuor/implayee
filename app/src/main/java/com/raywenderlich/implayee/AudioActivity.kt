package com.raywenderlich.implayee

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button


/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class AudioActivity : AppCompatActivity() {
    var playPauseAudio: Button? = null
    var restartAudio: Button? = null
    val mp = MediaPlayer()
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

    fun initViews() {
        //play / pause audio button (mediaplayer class)
        playPauseAudio = findViewById(R.id.playPauseAudio)

        //restart Audio button
        restartAudio = findViewById(R.id.restartAudio)
    }

    fun onClicks() {
        playPauseAudio?.setOnClickListener({
            if (mp.isPlaying) {
                mp.pause()
                playPauseAudio?.text = "Play Audio"
            } else {
                mp.start()
                playPauseAudio?.text = "Pause Audio"
            }

        })
        restartAudio?.setOnClickListener({
            mp.seekTo(0)
            mp.start()
        })
    }
}