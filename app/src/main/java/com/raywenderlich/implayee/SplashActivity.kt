/*
 * Copyright 2018 Google, Inc.
 *
 * ...
 */
package com.raywenderlich.implayee

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var triggerLaunch = Intent(this, MainActivity::class.java)
        startActivity(triggerLaunch)
        finish()
    }
}