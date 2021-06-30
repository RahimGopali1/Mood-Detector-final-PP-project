package com.example.Mood_Detector.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Mood_Detector.R
import com.example.Mood_Detector.data.Message
import com.example.Mood_Detector.utils.BotResponse
import com.example.Mood_Detector.utils.Constants.OPEN_GOOGLE
import com.example.Mood_Detector.utils.Constants.OPEN_SEARCH
import com.example.Mood_Detector.utils.Constants.RECEIVE_ID
import com.example.Mood_Detector.utils.Constants.SEND_ID
import com.example.Mood_Detector.utils.Time
import kotlinx.android.synthetic.main.activity_chatbot2.*
import kotlinx.coroutines.*

class chatbotActivity : AppCompatActivity() {
    private val TAG = "chatbotActivity"

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Bob")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot2)

        recyclerView()

        clickEvents()
        val preference = applicationContext.getSharedPreferences("Test", 0)
        val username = preference.getString("username","user")

        customMessage("Hello! $username I'm Bob, How may i help you?")
    }
    private fun clickEvents(){
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }
    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage() {
        val message =et_message.text.toString()
        val timeStamp = Time.timestamp()

        if (message.isNotEmpty()) {
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount -1)

            botResponse(message)
        }
    }
    private fun botResponse(message: String) {
        val timeStamp = Time.timestamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                when(response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
        private fun customMessage(message: String) {

            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main) {
                    val timeStamp = Time.timestamp()
                    adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }
