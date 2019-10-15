package com.example.pcremotecontrol

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Socket

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val outStream = clientSock.getOutputStream()
//        val inputStream = clientSock.getInputStream()

        btnConnection.setOnClickListener {
            val ip: String = editIP.text.toString()
            val port: Int = Integer.parseInt(editPort.text.toString())
            val context: Context = applicationContext

            val thread = SimpleThread(context, ip, port)
            thread.start()
        }
    }
}

class SimpleThread(private val context: Context, private val ip: String, private val port: Int): Thread() {
    override fun run() {
        println("${Thread.currentThread()} has run.")

        val clientSock = Socket(ip, port)

        val socketObject = SocketObject
        socketObject.socket = clientSock
        println(SocketObject.socket)

//        val outStream = clientSock.getOutputStream()
//        val inputStream = clientSock.getInputStream()
        val outStream = socketObject.socket.getOutputStream()
        val inputStream = socketObject.socket.getInputStream()

        val msgConnectRequest = "REQUEST_CONNECT"
        outStream.write(msgConnectRequest.toByteArray())
        println("메세지 전송 완료")

        while(true) {
            val available = inputStream.available()

            if(available > 0) {
                println("메세지 수신 완료")
                val msgArr = ByteArray(available)

                inputStream.read(msgArr)

                val revMsg = String(msgArr)
                println("Message: ${revMsg}")

                val intent = Intent(context, MainControlActivity::class.java)
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))

                break
            }
        }
    }
}
