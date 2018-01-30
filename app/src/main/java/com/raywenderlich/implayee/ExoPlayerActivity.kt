/*
 * Copyright 2018 Google, Inc.
 *
 * ...
 */
package com.raywenderlich.implayee

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ExoPlayerActivity : AppCompatActivity() {

    private var simpleExoPlayerView: SimpleExoPlayerView? = null
    private var exoPlayer: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_player)
        simpleExoPlayerView = findViewById<SimpleExoPlayerView>(R.id.player_view)
        simpleExoPlayerView?.requestFocus()


        var bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: AdaptiveTrackSelection.Factory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        //this is the player
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        //setting the player to the view
        simpleExoPlayerView?.player = exoPlayer
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this,
                Util.getUserAgent(this, this.getString(R.string.app_name)), bandwidthMeter)
        // Produces Extractor instances for parsing the media data.
        val extractorsFactory = DefaultExtractorsFactory()
        // This is the MediaSource representing the media to be played.
        val onlineFeed = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        val videoSource = ExtractorMediaSource(Uri.parse(onlineFeed),
                dataSourceFactory, extractorsFactory, null, null)
        // Prepare the player with the source.
        exoPlayer?.prepare(videoSource)
    }

    override fun onStop() {
        super.onStop()
        exoPlayer?.release()
    }

}
