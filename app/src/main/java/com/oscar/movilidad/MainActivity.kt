package com.oscar.movilidad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blongho.country_data.World
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        World.init(applicationContext)
    }
}