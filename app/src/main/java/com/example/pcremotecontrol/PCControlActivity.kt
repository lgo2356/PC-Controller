package com.example.pcremotecontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_control.*

class PCControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        var downX = 0f
        var downY = 0f
        var moveX = 0f
        var moveY = 0f
        
//        layoutMouseSpace.setOnTouchListener { v, event ->
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    downX = event.rawX
//                    downY = event.rawY
//
//                    Log.d("ACTION", "down x: " + downX + "\n" + "down y: " + downY)
//                }
//
//                MotionEvent.ACTION_MOVE -> {
////                    val moveX = downX - event.rawX
//                    Log.d("ACTION", "Test result: " + (event.rawX - moveX))
//
////                    val cmd: String = "mouse" + "|" + (event.rawX - moveX) + "|" + (event.rawY - moveY)
//
//                    if(abs(event.rawX - moveX) <= 40 && abs(event.rawY - moveY) <= 40) {
//                        Log.d("ACTION", "EVENT")
//                        val thread = MouseThread((event.rawX - moveX), (event.rawY - moveY))
//                        thread.start()
//                    }
//
//                    moveX = event.rawX
//                    moveY = event.rawY
//                }
//            }
//
//            true
//        }

        btnUp.setOnClickListener {
            val cmd = "up"
            val thread = SendThread(cmd)

            thread.start()
        }

        btnDown.setOnClickListener {
            val cmd = "down"
            val thread = SendThread(cmd)

            thread.start()
        }

        btnLeft.setOnClickListener {
            val cmd = "left"
            val thread = SendThread(cmd)

            thread.start()
        }

        btnRight.setOnClickListener {
            val cmd = "right"
            val thread = SendThread(cmd)

            thread.start()
        }

        btnSpace.setOnClickListener {
            val cmd = "space"
            val thread = SendThread(cmd)

            thread.start()
        }

        btnEnter.setOnClickListener {
            val cmd = "enter"
            val thread = SendThread(cmd)

            thread.start()
        }
    }
}

class SendThread(private val cmd: String): Thread() {
    override fun run() {
        val socketObject = SocketObject.socket
        var msg = "EMPTY"

        when(cmd) {
            "up" -> msg = "KEYBOARD_UP"
            "down" -> msg = "KEYBOARD_DOWN"
            "left" -> msg = "KEYBOARD_LEFT"
            "right" -> msg = "KEYBOARD_RIGHT"
            "space" -> msg = "KEYBOARD_SPACE"
            "enter" -> msg = "KEYBOARD_ENTER"
        }

        socketObject.getOutputStream().write(msg.toByteArray())
        println("Message 전송 완료")
    }
}

class MouseThread(private val x: Float, private val y: Float): Thread() {
    override fun run() {
        val socketObject = SocketObject.socket
        var msg: String = "MOUSEMOVE_" + x + "_" + y + "_"

        socketObject.getOutputStream().write(msg.toByteArray())
//        println("Message 전송 완료")
    }
}