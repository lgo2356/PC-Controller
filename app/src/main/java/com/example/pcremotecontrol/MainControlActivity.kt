package com.example.pcremotecontrol

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_control.*

class MainControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_control)

        btnKeyboardMouse.setOnClickListener {
            val intent = Intent(this, PCControlActivity::class.java)
            startActivity(intent)
        }
    }
}

