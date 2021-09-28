package com.example.recyclerviewapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var clMain: ConstraintLayout
    lateinit var guessFiled: EditText
    lateinit var guessButton: Button
    lateinit var messages: ArrayList<String>
    lateinit var mGuess: TextView

    var answer = 0
    var guesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answer = Random.nextInt(10)

        clMain = findViewById(R.id.clMain)
        messages = ArrayList()
        mGuess = findViewById(R.id.mGuess)

        rvMessages.adapter = MessageAdapter(this,messages)
        rvMessages.layoutManager = LinearLayoutManager(this)

        guessFiled = findViewById(R.id.GuessField)
        guessButton = findViewById(R.id.GuessButton)

        guessButton.setOnClickListener { add() }
    }

    fun add(){
        val msg = guessFiled.text.toString()
        if(msg.isNotEmpty()){
            if(guesses > 0 ){
                if(msg.toInt() == answer){
                    disableEntry()
                    showAlertDialog("YOU WIN!!, PLAY AGAIN?")
                }else{
                    guesses--
                    messages.add("YOU GUESSED $msg")
                    messages.add("YOU HAVE $guesses GUESSES LEFT")
                }
                if(guesses == 0){
                    disableEntry()
                    messages.add("YOU ARE LOSE, THE CORRECT ANSWER WAS $answer")
                    messages.add("GAME OVER")
                    showAlertDialog("YOU ARE LOSE, THE CORRECT ANSWER WAS $answer")
                }
            }
            guessFiled.text.clear()
            guessFiled.clearFocus()
            rvMessages.adapter?.notifyDataSetChanged()
        }else{
            Snackbar.make(clMain, "Please enter a number", Snackbar.LENGTH_LONG).show()
        }
    }
    private fun disableEntry(){
        guessButton.isEnabled = false
        guessButton.isClickable = false
        guessFiled.isEnabled = false
        guessFiled.isClickable = false
    }
    private fun showAlertDialog(title: String) {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(title)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
    }
}


