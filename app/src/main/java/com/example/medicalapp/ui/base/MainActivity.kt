package com.example.medicalapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medicalapp.R
import com.example.medicalapp.util.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPrefs.init(this)
    }
}