package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var clroot: ConstraintLayout
    private lateinit var guessField: EditText
    private lateinit var guessButton: Button
    private lateinit var messages: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clroot = findViewById(R.id.clroot)
        messages = ArrayList()

        recyclerV.adapter = MessageAdapter(this, messages)
        recyclerV.layoutManager = LinearLayoutManager(this)

        guessField = findViewById(R.id.guessField)
        guessButton = findViewById(R.id.guessButton)

        guessButton.setOnClickListener { addMessage() }

    }
    private fun addMessage(){
        val msg = guessField.text.toString()
        if(msg.isNotEmpty()){
            messages.add(msg)
            guessField.text.clear()
            guessField.clearFocus()
        }
    }
}

